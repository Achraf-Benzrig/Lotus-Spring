package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.ChatbotKnowledge;
import tn.esprit.spring.service.IChatBotService;

@RestController
@Api(tags = "ChatBot management")
@RequestMapping("/chatbot")
@CrossOrigin(origins = "http://localhost:8089")
public class ChatBotController {
	@Autowired
	IChatBotService chatbotService;
	
    // http://localhost:8089/WomenEmpowerment/chatbot/trainMe
    @PostMapping("/trainMe/{question}/{answer}")
    @ResponseBody
    public void trainMe(@PathVariable("question") String question,@PathVariable("answer") String answer) {
    	
        chatbotService.trainMe(question,answer);
    }
    
    // http://localhost:8089/WomenEmpowerment/chatbot/addKnowledge
    @PostMapping("/addKnowledge")
    @ResponseBody
    public void addKnowledge(@RequestBody ChatbotKnowledge request) {
    	
        chatbotService.addKnowledge(request);
    }
    
    // http://localhost:8089/WomenEmpowerment/chatbot/updateKnowledge
    @PutMapping("/updateKnowledge")
    @ResponseBody
    public void updateKnowledge(@RequestBody ChatbotKnowledge request) {
    	
        chatbotService.updateKnowledge(request);
    }
    
    // http://localhost:8089/WomenEmpowerment/chatbot/deleteKnowledge/
    @DeleteMapping("/deleteKnowledge/{id}")
    @ResponseBody
    public void updateKnowledge(@PathVariable("id") long id) {
    	
        chatbotService.deleteKnowledge(id);
    }
    
    // http://localhost:8089/WomenEmpowerment/chatbot/setKnowledgeActive/
    @PutMapping("/setKnowledgeActive/{id}")
    @ResponseBody
    public void setKnowledgeActive(@PathVariable("id") long id) {
    	
        chatbotService.setKnowledgeActive(id);
    }
    
    // http://localhost:8089/WomenEmpowerment/chatbot/setKnowledgeInactive/
    @PutMapping("/setKnowledgeInactive/{id}")
    @ResponseBody
    public void setKnowledgeInactive(@PathVariable("id") long id) {
    	
        chatbotService.setKnowledgeInactive(id);
    }
    
    
    // http://localhost:8089/WomenEmpowerment/chatbot/answer/
    @GetMapping("/answer/{answer}/{email}")
    @ResponseBody
    public String answer(@PathVariable("answer") String answer,@PathVariable("email") String email) {
    	
        return chatbotService.answer(answer,email);
    }

}
