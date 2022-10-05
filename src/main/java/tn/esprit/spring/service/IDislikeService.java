package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Dislike;
import tn.esprit.spring.entity.Likes;

public interface IDislikeService {
	public void AjouterDislik(Dislike dislik,Long idCo ,String email) ;
	public List<Dislike> getDislikes();
	public void deleteDislikes(Long id);

}
