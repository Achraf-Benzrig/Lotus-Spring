package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Rating;
import tn.esprit.spring.repository.PublicationRepository;
import tn.esprit.spring.repository.RatingRepository;

@Service
public class RatingService implements IRatingService{
@Autowired
RatingRepository ratrepo;
@Autowired 
PublicationRepository pubrepo;
@Autowired
UserRepository userrepo;
	
	@Override
	public void AjouterRat(Rating rat, Long idPub,String email) {
		User u = userrepo.findByEmail(email).orElse(null);

			
		
			  
			
		
Publication p = pubrepo.findById(idPub).orElse(null);
		
		Rating r = ratrepo.save(rat);
		r.setRateBy(email);
		r.setPublications(p);
		ratrepo.save(r);
	}


	@Override
	public void deleteRate(Long id) {
		Rating p = ratrepo.findById(id).orElse(null);
		ratrepo.delete(p);
		
	}


	@Override
	public void updateRate(Rating rat, Long idPub, String email) {
Publication p = pubrepo.findById(idPub).orElse(null);
		
		Rating r = ratrepo.save(rat);
		r.setRateBy(email);
		r.setPublications(p);
		ratrepo.save(r);
	}

	@Override
	public float RateAVG(Long pub) {
		// TODO Auto-generated method stub
		return ratrepo.RateAVG(pub);
	}


}
