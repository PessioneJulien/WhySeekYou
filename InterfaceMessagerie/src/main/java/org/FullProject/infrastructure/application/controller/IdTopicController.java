package org.FullProject.infrastructure.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.FullProject.infrastructure.application.model.Topic;
import org.FullProject.infrastructure.application.service.TopicService;

@RestController
public class IdTopicController {
    @Autowired
    private TopicService topicService;

    @PutMapping("/topic/{idUsers1}/setTopic/{idUsers2}")
    public Topic setTopic(@PathVariable Long idUsers1, @PathVariable Long idUsers2) {
        return topicService.setTopic(idUsers1, idUsers2);
    }
    @PostMapping("/topic/{idUsers1}/setMessageEnAttente/{idUsers2}")
    public void setMessageEnAttente(@PathVariable Long idUsers1, @PathVariable Long idUsers2, @RequestBody String message) {
        topicService.setMessageEnAttente(idUsers1, idUsers2, message);
    }
    @PostMapping("/topic/{idUsers1}/setPath/{idUsers2}")
    public void setPath(@PathVariable Long idUsers1, @PathVariable Long idUsers2, @RequestBody String path) {
        topicService.setPath(idUsers1, idUsers2, path);
    }

    @GetMapping("/topic/{idUsers1}/getTopic/{idUsers2}")
    public Topic getTopic(@PathVariable Long idUsers1, @PathVariable Long idUsers2) {
        return topicService.getTopic(idUsers1, idUsers2);
    }
    @GetMapping("/topic/{idUsers1}/getTopicId/{idUsers2}")
    public Long getTopicId(@PathVariable Long idUsers1, @PathVariable Long idUsers2) {
        return topicService.getTopicId(idUsers1, idUsers2);
    }
    @GetMapping("/topic/{idUsers1}/getMessageEnAttente/{idUsers2}")
    public String getMessageEnAttente(@PathVariable Long idUsers1, @PathVariable Long idUsers2) {
        return topicService.getMessageEnAttente(idUsers1, idUsers2);
    }
    @GetMapping("/topic/{idUsers1}/getPath/{idUsers2}")
    public String getPath(@PathVariable Long idUsers1, @PathVariable Long idUsers2) {
        return topicService.getPath(idUsers1, idUsers2);
    }

    @DeleteMapping("/topic/{idUsers1}/deleteMessageEnAttente/{idUsers2}")
    public void deleteMessageEnAttente(@PathVariable Long idUsers1, @PathVariable Long idUsers2) {
        topicService.deleteMessageEnAttente(idUsers1, idUsers2);
    }

}
