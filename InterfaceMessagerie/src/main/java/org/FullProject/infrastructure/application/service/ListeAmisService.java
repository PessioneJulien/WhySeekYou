package org.FullProject.infrastructure.application.service;

import lombok.Data;
import org.FullProject.infrastructure.application.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.FullProject.infrastructure.application.model.ListeAmis;
import org.FullProject.infrastructure.application.model.Users;
import org.FullProject.infrastructure.application.repository.ListeAmisRepository;

import java.util.ArrayList;
import java.util.List;


@Data
@Service
public class ListeAmisService {
    @Autowired
    private ListeAmisRepository listeAmisRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;


    public List<String> getListeAmis(Long currentUserId) {
        Users currentUser = usersService.getUsers(currentUserId).get();
        List<ListeAmis> currentListeAmis = currentUser.getListeAmis();
        Long size = Long.valueOf(currentUser.getListeAmis().size());
        List<String> listAmisAcceptes = new ArrayList<String>();
        for (ListeAmis amis : currentListeAmis) {
            if (amis.getAccepte().equals(true)) {
                Long idUser2 = amis.getIdUser2();
                Users amiUser = usersService.getUsers(idUser2).get();

                String nom = amiUser.getNom();
                String prenom = amiUser.getPrenom();

                listAmisAcceptes.add(nom + " " + prenom);
            }
        }
        return listAmisAcceptes;
    }

    public List<String> getListeAmisEnAttente(Long currentUserId) {
        Users currentUser = usersService.getUsers(currentUserId).get();
        List<ListeAmis> currentListeAmis = currentUser.getListeAmis();
        Long size = Long.valueOf(currentUser.getListeAmis().size());
        List<String> listAmisEnAttente = new ArrayList<String>();
        for (ListeAmis amis : currentListeAmis) {
            if (amis.getEnAttente().equals(true) && !amis.getId_demandeur().equals(currentUserId)) {
                Long idUser2 = amis.getIdUser2();
                Users amiUser = usersService.getUsers(idUser2).get();

                String nom = amiUser.getNom();
                String prenom = amiUser.getPrenom();

                listAmisEnAttente.add(nom + " " + prenom);
            }
        }
        return listAmisEnAttente;
    }

    public List<ListeAmis> getListeAmisSpecifique (Long idDemandeur, Long idReceveur) {
        List<Users> listUsers = usersService.getUsersRelationAmis(idDemandeur, idReceveur);
        List<ListeAmis> listeFinale = new ArrayList<ListeAmis>();
        List<ListeAmis> listeAmisU1 = listUsers.get(0).getListeAmis();
        List<ListeAmis> listeAmisU2 = listUsers.get(1).getListeAmis();
        for (ListeAmis listeAmis : listeAmisU1) {
            if (listeAmis.getIdUser2().equals(idReceveur)) {
                listeFinale.add(listeAmis);
            }
        }
        for (ListeAmis listeAmis : listeAmisU2) {
            if (listeAmis.getIdUser2().equals(idDemandeur)) {
                listeFinale.add(listeAmis);
            }
        }
        listUsers.get(1).getListeAmis();
        return listeFinale;
    }
    public Long getNombreAmis(Long currentUserId) {
        Users currentUser = usersService.getUsers(currentUserId).get();
        List<ListeAmis> currentListeAmis = currentUser.getListeAmis();
        List<String> listNombre = new ArrayList<String>();
        for (ListeAmis amis : currentListeAmis) {
            if (amis.getAccepte().equals(true)) {
                listNombre.add(" ");
            }
        }
        Long size = Long.valueOf(listNombre.size());
        return size;
    }

    public String accepterAmiEnAttente(Long currentUserId, Long idFuturAmi) {
        Users currentUser = usersService.getUsers(currentUserId).get();
        Users futurAmi = usersService.getUsers(idFuturAmi).get();
        List<ListeAmis> listeAmis = currentUser.getListeAmis();
        for (ListeAmis amis : listeAmis) {
            if (amis.getIdUser2().equals(idFuturAmi)) {
                if (!amis.getId_demandeur().equals(currentUserId)) {
                    if (amis.getEnAttente().equals(true)) {
                        amis.setEnAttente(false);
                        amis.setAccepte(true);
                        listeAmisRepository.save(amis);
                        List<ListeAmis> listeFuturAmis = futurAmi.getListeAmis();
                        for (ListeAmis amisFutur : listeFuturAmis) {
                            if (amisFutur.getIdUser2().equals(currentUserId)) {
                                amisFutur.setEnAttente(false);
                                amisFutur.setAccepte(true);
                                listeAmisRepository.save(amisFutur);
                                String validation = "Vous êtes maintenant amis avec " + futurAmi.getNom() + " " + futurAmi.getPrenom();
                                return validation;
                            }
                        }
                    }
                }
            }
        }
        return "Erreur";
    }

    public String amisBloquer(Long currentUserId, Long idUserABloquer, Boolean bloquerBoolean) {
        Users currentUsers = usersService.getUsers(currentUserId).get();
        Users usersABloquer = usersService.getUsers(idUserABloquer).get();

        List<ListeAmis> listeAmis = currentUsers.getListeAmis();
        for (ListeAmis amis : listeAmis) {
            if (amis.getIdUser2().equals(idUserABloquer)) {
                if (amis.getBloquee().equals(true)) {
                    if (amis.getIdBloqueur().equals(currentUserId)) {
                        if (bloquerBoolean.equals(true)) {
                            return "Vous avez déjà bloqué cet utilisateur";
                        } else {
                            List<ListeAmis> listeAmisReverse = usersABloquer.getListeAmis();
                            for (ListeAmis amisReverse : listeAmisReverse) {
                                if (amisReverse.getIdUser2().equals(currentUserId) && amisReverse.getIdBloqueur().equals(currentUserId)) {
                                    listeAmisRepository.deleteById(amisReverse.getId());
                                    break;
                                }
                            }
                            listeAmisRepository.deleteById(amis.getId());
                            return "Vous avez débloqué cet utilisateur";
                        }
                    } else {
                        return "Utilisateur non disponible";
                    }
                }
                amis.setBloquee(true);
                amis.setIdBloqueur(currentUserId);
                amis.setAccepte(false);
                amis.setEnAttente(false);
                listeAmisRepository.save(amis);
                List<ListeAmis> listeAmisReverse = usersABloquer.getListeAmis();
                for (ListeAmis amisReverse : listeAmisReverse) {
                    if (amisReverse.getIdUser2().equals(currentUserId)) {
                        amisReverse.setBloquee(true);
                        amisReverse.setIdBloqueur(currentUserId);
                        amisReverse.setAccepte(false);
                        amisReverse.setEnAttente(false);
                        listeAmisRepository.save(amisReverse);
                        return "Utilisateur bloqué";
                    }
                }
            }
        }
        return null;
    }

    public String ajoutAmisMail(Long idDemandeur, String mailReceveur) {
        Long idReceveur = usersService.getUsersMail(mailReceveur).get().getId();
        return ajoutAmisId(idDemandeur, idReceveur);
    }

    public String ajoutAmisId(Long idDemandeur, Long idReceveur) {
        Users usersDemandeur = usersService.getUsers(idDemandeur).get();
        Users usersReceveur = usersService.getUsers(idReceveur).get();

        List<ListeAmis> listeAmis = usersDemandeur.getListeAmis();
        for (ListeAmis amis : listeAmis) {
            if (amis.getIdUser2().equals(idReceveur) && amis.getId_demandeur().equals(idDemandeur)) {
                if (amis.getAccepte().equals(true)) {
                    return "deja ami";
                } else if (amis.getEnAttente().equals(true)) {
                    return "votre demande est en attente";

                } else if (amis.getBloquee().equals(true)) {
                    return "vous avez bloqué cet utilisateur";
                }
            } else if (amis.getIdUser2().equals(idReceveur) && amis.getId_demandeur().equals(idReceveur)) {
                if (amis.getAccepte().equals(true)) {
                    return "deja ami";
                } else if (amis.getEnAttente().equals(true)) {
                    amis.setAccepte(true);
                    amis.setEnAttente(false);
                    listeAmisRepository.save(amis);
                    List<ListeAmis> listeAmisReverse = usersReceveur.getListeAmis();
                    for (ListeAmis amisReverse : listeAmisReverse) {
                        if (amisReverse.getIdUser2().equals(idDemandeur) && amisReverse.getId_demandeur().equals(idReceveur)) {
                            amisReverse.setAccepte(true);
                            amisReverse.setEnAttente(false);
                            listeAmisRepository.save(amisReverse);
                            return "vous etes maintenant ami";
                        }
                    }
                }
            } else if (amis.getBloquee().equals(true)) {
                return "utilisateur non disponible";
            }
        }
        //creation de la demande d'ami
        ListeAmis listeAmis1 = new ListeAmis();
        listeAmis1.setUsers(usersDemandeur);
        listeAmis1.setIdUser2(idReceveur);
        listeAmis1.setId_demandeur(idDemandeur);
        listeAmis1.setEnAttente(true);
        listeAmis1.setBloquee(false);
        listeAmis1.setAccepte(false);
        listeAmis1.setIdBloqueur(0L);
        listeAmisRepository.save(listeAmis1);

        ListeAmis listeAmisBis = new ListeAmis();
        listeAmisBis.setUsers(usersReceveur);
        listeAmisBis.setIdUser2(idDemandeur);
        listeAmisBis.setId_demandeur(idDemandeur);
        listeAmisBis.setEnAttente(true);
        listeAmisBis.setBloquee(false);
        listeAmisBis.setAccepte(false);
        listeAmisBis.setIdBloqueur(0L);
        listeAmisRepository.save(listeAmisBis);
        return "une demande à été envoyé";
    }




}
