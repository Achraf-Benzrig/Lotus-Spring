package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CommentaireTraining;



@Repository
public interface CommentaireTrainingRepository extends CrudRepository <CommentaireTraining,Long> {
	
	

}
