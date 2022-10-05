package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Likes;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.repository.CommentairePubRepository;
import tn.esprit.spring.repository.LikesRepository;
@Service
public class LikesService implements ILikesService {
@Autowired
CommentairePubRepository comrepo;
@Autowired
UserRepository userrepo;
@Autowired
LikesRepository likrepo;
	
	
	
	@Override
	public void AjouterLik(Likes lik, Long idCo, String email){
		
		User u = userrepo.findByEmail(email).orElse(null);
		boolean userExists = userrepo
                .findByEmail(u.getEmail())
                .isPresent();
		boolean likedbyExists = likrepo
                .findByLikedBy(email)
                .isPresent();
		
	
	

		  if (userExists ) {
			
		  
			
			  CommentairePub p = comrepo.findById(idCo).orElse(null);
			  
				 
				
				Likes L = likrepo.save(lik);
				
			L.setLikedBy(email);
			L.setLikes(L.getLikes()+1);
			L.setCommentairesPubs(p);
			p.setLikesss(p.getLikesss()+1);
			
			likrepo.save(L);}
			else{}
			  
				  
			
		  
}
	



	@Override
	public List<Likes> getLikes() {
		// TODO Auto-generated method stub
		return likrepo.findAll();
	}



	@Override
	public void deleteLikes(Long id) {
		Likes l = likrepo.findById(id).orElse(null);
		likrepo.delete(l);
		}



	@Override
	public Long getlikpar(Long publ) {
		long l =likrepo.countBylikes(publ);
		System.out.println(l);
		return likrepo.countBylikes(publ);
	
	
		
		
		
	
	}



	@Override
	public List<String> displayLiedby(Long pub) {
		// TODO Auto-generated method stub
		return likrepo.displaylikedby(pub);
	}
	
	

}
