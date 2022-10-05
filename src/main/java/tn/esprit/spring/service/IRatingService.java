package tn.esprit.spring.service;

import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Rating;

public interface IRatingService {
	public void AjouterRat(Rating rat ,Long idPub,String email) ;

	void deleteRate(Long id);

	void updateRate(Rating rat,Long idPub,String email);
	public float RateAVG(Long pub);


}
