package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.CommentaireTraining;



public interface CommentaireTrainingService {
	
	void ajouterComment(CommentaireTraining comment);
	CommentaireTraining updateComment(CommentaireTraining com);
	void deleteComment(Long id);

	List<CommentaireTraining> retrieveAllComments();
	
	CommentaireTraining retrieveComments(Long id);
	public void affecterCommentaireFormation(Long idComment,Long idFormation);
	

}
