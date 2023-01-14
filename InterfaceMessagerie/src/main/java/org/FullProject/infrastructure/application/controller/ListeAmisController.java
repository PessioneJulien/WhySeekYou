package org.FullProject.infrastructure.application.controller;

import org.FullProject.infrastructure.application.service.ListeAmisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
    liste des fonctions :
    - afficher tous les amis
    - afficher les demandes en attente
    - afficher le nombre d'amis
    - ajouter amis par id
    - ajouter amis par mail
    - accepter une demande
    - bloquer/débloquer un ami
 */

@RestController
public class ListeAmisController {
    @Autowired
    private ListeAmisService listeAmisService;

    //AFFICHER AMIS
        //tous les amis
    @GetMapping("/users/{idUsers}/listeAmis")
    public List<String> getListeAmis(@PathVariable Long idUsers) {
        return listeAmisService.getListeAmis(idUsers);
    }
        //amis en attente
    @GetMapping("/users/{idUsers}/listeAmisEnAttente")
    public List<String> getListeAmisEnAttente(@PathVariable Long idUsers) {
        return listeAmisService.getListeAmisEnAttente(idUsers);
    }
        //nombre d'amis
    @GetMapping("/users/{idUsers}/nombreAmis")
    public Long getNombreAmis(@PathVariable Long idUsers) {
        return listeAmisService.getNombreAmis(idUsers);
    }

    //CREATION MODIFICATION DEMANDE AMIS
        //ajout par id
    @PutMapping("/users/{idUsers1}/ajoutAmisId/{idUsers2}")
    public String ajoutAmisId(@PathVariable Long idUsers1, @PathVariable Long idUsers2) {
        return listeAmisService.ajoutAmisId(idUsers1, idUsers2);
    }
    // FROM PUT TO POST TTENTION LOOSER
        //ajout par mail
    @PostMapping("/users/{idDemandeur}/ajoutAmisMail")
    public String ajoutAmisMail(@PathVariable Long idDemandeur, @RequestBody String mailReceveur) {
        return listeAmisService.ajoutAmisMail(idDemandeur, mailReceveur);
    }
        //Accepter une demande
    @PutMapping("/users/{currentUserId}/accepterAmiEnAttente/{idFuturAmi}")
    public String accepterAmiEnAttente(@PathVariable Long currentUserId, @PathVariable Long idFuturAmi) {
        return listeAmisService.accepterAmiEnAttente(currentUserId, idFuturAmi);
    }
        //bloquer(true)/débloquer(false) un ami
    @PutMapping("/users/{currentUserId}/bloquer/{idUserABloquer}/{bloquerBoolean}")
    public String amisBloquer(@PathVariable Long currentUserId, @PathVariable Long idUserABloquer, @PathVariable Boolean bloquerBoolean) {
        return listeAmisService.amisBloquer(currentUserId, idUserABloquer, bloquerBoolean);
    }

    /*@PutMapping("/users/{id}/listeAmis/ajout")
    public ListeAmis ajoutListeAmis(@RequestBody ListeAmis listeAmis, @PathVariable Long idUsers) {
        return listeAmisService.saveListeAmis(listeAmis, idUsers);
    }*/
    /*@PutMapping("/users/{id}/listeAmis/ajout")
    public ListeAmis ajoutListeAmis(@PathVariable Long idUsers) {
        return listeAmisService.saveListeAmis(idUsers);
    }*/

}
