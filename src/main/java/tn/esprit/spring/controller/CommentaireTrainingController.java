package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.CommentaireTraining;
import tn.esprit.spring.entity.Training;
import tn.esprit.spring.service.CommentaireTrainingService;


@RestController
@RequestMapping("/CommentTraining")
public class CommentaireTrainingController {
	
	@Autowired
	CommentaireTrainingService commentService;
	
	//http://localhost:8089/WomenEmpowerment/CommentTraining/add-CommentTraining
	@PostMapping("/add-CommentTraining")
	@ResponseBody
	public void ajouterCommentFormation(@RequestBody CommentaireTraining comment)
	{
		
		commentService.ajouterComment(comment);
		
	}
	
	 //http://localhost:8089/WomenEmpowerment/CommentTraining/update-CommentTraining
	@PutMapping("/update-CommentTraining")
	@ResponseBody
	public CommentaireTraining updateComment(@RequestBody CommentaireTraining com) {
	return commentService.updateComment(com);
	}
	
	//http://localhost:8089/WomenEmpowerment/CommentTraining/remove-CommentTraining/{CommentTraining-id}
	@DeleteMapping("/remove-CommentTraining/{CommentTraining-id}")
	@ResponseBody
	public void deleteComment(@PathVariable("CommentTraining-id") Long CommentTrainingId) {
		commentService.deleteComment(CommentTrainingId);
	}
  
	
	//http://localhost:8089/WomenEmpowerment/CommentTraining/retrieve-all-CommentTraining
	@GetMapping("/retrieve-all-CommentTraining")
	@ResponseBody
	public List<CommentaireTraining> retrieveAllComments (){
		
	List<CommentaireTraining> listCommentTraining = commentService.retrieveAllComments();
	return listCommentTraining;
	}
	
	//http://localhost:8089/WomenEmpowerment/CommentTraining/retrieve-CommentTraining/{CommentTraining-id}
	@GetMapping("/retrieve-CommentTraining/{CommentTraining-id}")
	@ResponseBody
	public CommentaireTraining retrieveComments(@PathVariable("CommentTraining-id") Long CommentTrainingId) {
	return commentService.retrieveComments(CommentTrainingId);
	}
	
	//	
	@PostMapping("/affecterCommentFormation/{id}/{idFormation}") 
	@ResponseBody 
	public void affecterCommentaireFormation(@PathVariable("id")Long id,@PathVariable("idFormation") Long idFormation) {
		commentService.affecterCommentaireFormation(id, idFormation);
		
	}
}
