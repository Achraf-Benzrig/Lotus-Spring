package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.ChatbotKnowledge;

public interface ChatBotRepository extends CrudRepository<ChatbotKnowledge,Long>{
	
    @Transactional
    @Modifying
    @Query("UPDATE ChatbotKnowledge a " + "SET a.active = TRUE WHERE a.id = ?1")
    void setKnowledgeActive(long id);
    
    
    @Transactional
    @Modifying
    @Query("UPDATE ChatbotKnowledge a " + "SET a.active = FALSE WHERE a.id = ?1")
    void setKnowledgeInactive(long id);

}
