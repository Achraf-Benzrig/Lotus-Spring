package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Likes;
import tn.esprit.spring.entity.Publication;

public interface ILikesService {
	public void AjouterLik(Likes lik,Long idCo ,String email) ;
	public List<Likes> getLikes();
	public void deleteLikes(Long id);
	public Long getlikpar(Long pub);
	public List<String> displayLiedby(Long publ);
	

}
