package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.CommentaireTraining;
import tn.esprit.spring.entity.Training;
import tn.esprit.spring.repository.CommentaireTrainingRepository;
import tn.esprit.spring.repository.TrainingRepository;

@Service
public class CommentaireTrainingServiceImpl implements  CommentaireTrainingService{
	
	@Autowired
	private CommentaireTrainingRepository commentRepo;
	@Autowired
	private TrainingRepository trainingrepo;

	@Override
	public void ajouterComment(CommentaireTraining comment) {
		
		commentRepo.save(comment);
		
	}

	@Override
	public CommentaireTraining updateComment(CommentaireTraining com) {
		
		return commentRepo.save(com);
	}

	@Override
	public void deleteComment(Long id) {
		commentRepo.deleteById(id);
		
	}

	@Override
	public List<CommentaireTraining> retrieveAllComments() {
	
		return (List<CommentaireTraining>) commentRepo.findAll();
	}

	@Override
	public CommentaireTraining retrieveComments(Long id) {
		
		return commentRepo.findById(id).get() ;
	}

	@Override
	public void affecterCommentaireFormation(Long idComment, Long idFormation) {
		CommentaireTraining comment= commentRepo.findById(idComment).get();
		Training trainings= trainingrepo.findById(idFormation).get();
		comment.setTrainings(trainings);
		commentRepo.save(comment);
		
		
	}

}
