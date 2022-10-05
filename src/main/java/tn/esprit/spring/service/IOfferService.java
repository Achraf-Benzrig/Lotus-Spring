package tn.esprit.spring.service;

import java.util.List;

//import org.springframework.data.domain.Pageable;

import tn.esprit.spring.entity.Offer;

public interface IOfferService {
	
	public void AddOffer(Offer f);
	public void DeleteOffer(Long idOffer);
	public List<Offer> getAllOffers();
	public void UpdateOffer(Offer f);
	
	public void AffecterOfferByUserId(Long IdOffer, String email);
	//Recherche:
	
	List<Offer> FindOfferByTitle(String Title); //les candidats filtre les offre is that true mouch el compo
	
	//filter: 
	public List<Offer> search(String keyword);
	
	public void JobApplication(Long IdOffer, String email);
	
	public List<Offer> suggestedOffer(Long idUser);
	List<Object[]> statistic();
	
	
	
	
	//public Page<Product> listAll(int pageNum);
	//public void UpdateOfferById(Long idOffer);
	//Offer FindOffer(int idOffer);
	//public void AfficherOffer();
	

	
}
