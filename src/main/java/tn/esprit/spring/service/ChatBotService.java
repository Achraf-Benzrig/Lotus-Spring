package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.ChatbotKnowledge;
import tn.esprit.spring.repository.ChatBotRepository;

@Service
public class ChatBotService implements IChatBotService{

	@Autowired
	ChatBotRepository myRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addKnowledge(ChatbotKnowledge k) {
		k.setActive(true);
		myRepository.save(k);
	}
	
	@Override
	public void updateKnowledge(ChatbotKnowledge k) {
		myRepository.save(k);
	}
	
	@Override
	public void deleteKnowledge(long id) {
		myRepository.deleteById(id);
	}

	@Override
	public void setKnowledgeActive(long id) {
		myRepository.setKnowledgeActive(id);
		
	}

	@Override
	public void setKnowledgeInactive(long id) {
		myRepository.setKnowledgeInactive(id);
		
	}

	@Override
	public void trainMe(String question, String answer) {
		ChatbotKnowledge k =new ChatbotKnowledge();
		k.setAnswer(answer);
		k.setQuestion(question);
		k.setActive(false);
		myRepository.save(k);
		
	}

	@Override
	public String answer(String question ,String email) {
		User u = userRepository.findByEmail(email).orElse(null);
		HashMap<String, Float> listOfFloats = new HashMap<String, Float>();
		float max =0 ; 
		String emotions = "";
		listOfFloats.put("sad", u.getUserEmotions().getSad());
		listOfFloats.put("happy", u.getUserEmotions().getHappy());
		listOfFloats.put("fear",u.getUserEmotions().getFear());
		listOfFloats.put("anger", u.getUserEmotions().getAnger());
		listOfFloats.put("violence", u.getUserEmotions().getViolence());
		listOfFloats.put("suicide", u.getUserEmotions().getSuicide());
		for (String i : listOfFloats.keySet()) {
			if(listOfFloats.get(i)>max){
				  max = listOfFloats.get(i) ; 
				  emotions = i;
			  }
		}
		
		if("i need help".contains(question) || "i need your help".contains(question) || "help".contains(question)){
			return "what's wrong are you "+emotions;
		}
		 System.out.println("key: " + emotions + " value: " + max);
		
		List<ChatbotKnowledge> ck = (List<ChatbotKnowledge>) myRepository.findAll();
		
		for (ChatbotKnowledge chatbotKnowledge : ck) {
			if(chatbotKnowledge.getActive() && chatbotKnowledge.getQuestion().toLowerCase().contains(question.toLowerCase())){
				return chatbotKnowledge.getAnswer();
			}
			
		}
		
		return "Sorry, Im dumb! How should I reply http://localhost:8089/WomenEmpowerment/chatbot/trainMe";
		
	}
	
	

}
