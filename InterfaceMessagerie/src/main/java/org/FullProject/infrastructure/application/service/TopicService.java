package org.FullProject.infrastructure.application.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.FullProject.infrastructure.application.model.ListeAmis;
import org.FullProject.infrastructure.application.model.Topic;
import org.FullProject.infrastructure.application.repository.TopicRepository;
import org.FullProject.infrastructure.application.repository.ListeAmisRepository;


import java.util.List;

@Data
@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ListeAmisService listeAmisService;
    @Autowired
    private ListeAmisRepository listeAmisRepository;


    /*    private Long id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    private List<ListeAmis> listeAmis;
    @Column(name = "path")
    private String path;
    @Column(name = "msgEnAttente")
    private String msgEnAttente;
    */

    //TOPIC
    public Topic setTopic(Long idDemandeur, Long idReceveur) {
        System.out.println("setTopic");
        List<ListeAmis> listAmis = listeAmisService.getListeAmisSpecifique(idDemandeur, idReceveur);
        Topic topic = new Topic();
        topic.setPath("path");
        topic.setMsgEnAttente("msgEnAttente");
        topicRepository.save(topic);
        topic.getId();
        listAmis.get(0).setTopic(topic);
        listAmis.get(1).setTopic(topic);
        topicRepository.save(topic);
        listeAmisRepository.save(listAmis.get(0));
        listeAmisRepository.save(listAmis.get(1));
        return topic;
    }

    public Topic getTopic(Long idDemandeur, Long idReceveur) {
        List<ListeAmis> listAmis = listeAmisService.getListeAmisSpecifique(idDemandeur, idReceveur);
        if (listAmis.get(0).getTopic() != null) {
            return listAmis.get(0).getTopic();
        } else {
            return setTopic(idDemandeur, idReceveur);
        }
    }
    public Long getTopicId(Long idDemandeur, Long idReceveur) {
        List<ListeAmis> listAmis = listeAmisService.getListeAmisSpecifique(idDemandeur, idReceveur);
        if (listAmis.get(0).getTopic() != null) {
            return listAmis.get(0).getTopic().getId();
        } else {
            return setTopic(idDemandeur, idReceveur).getId();
        }
    }

    //MESSAGE EN ATTENTE
    public void setMessageEnAttente (Long idDemandeur, Long idReceveur, String message) {
        Topic topic = getTopic(idDemandeur, idReceveur);
        topic.setMsgEnAttente(message);
        topic.setIdUserEnvoieMsgEnAttente(idDemandeur);
        topicRepository.save(topic);
    }
    public String getMessageEnAttente (Long idDemandeur, Long idReceveur) {
        Topic topic = getTopic(idDemandeur, idReceveur);
        if(topic.getMsgEnAttente() != null && topic.getIdUserEnvoieMsgEnAttente() != idDemandeur) {
            String tmp = topic.getMsgEnAttente();
            deleteMessageEnAttente(idReceveur, idDemandeur);
            return tmp;
        } else {
            return "Pas de message en attente";
        }
    }
    public void deleteMessageEnAttente (Long idDemandeur, Long idReceveur) {
        Topic topic = getTopic(idDemandeur, idReceveur);
        if(topic.getMsgEnAttente() != null && topic.getIdUserEnvoieMsgEnAttente() == idDemandeur) {
            topic.setMsgEnAttente(null);
            topic.setIdUserEnvoieMsgEnAttente(null);
            topicRepository.save(topic);
        }
    }

    //PATH
    public void setPath(Long idDemandeur, Long idReceveur, String path) {
        Topic topic = getTopic(idDemandeur, idReceveur);
        topic.setPath(path);
        topicRepository.save(topic);
    }
    public String getPath(Long idDemandeur, Long idReceveur) {
        Topic topic = getTopic(idDemandeur, idReceveur);
        return topic.getPath();
    }

}
