package tn.esprit.spring.service;

import tn.esprit.spring.entity.ChatbotKnowledge;

public interface IChatBotService {
	
	public void addKnowledge(ChatbotKnowledge k);
	public void updateKnowledge(ChatbotKnowledge k);
	public void deleteKnowledge(long k);
	void setKnowledgeActive(long id);
	void setKnowledgeInactive(long id);
	public void trainMe(String question,String answer);
	public String answer(String question,String email);
	

}
