package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Likes;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.TypePub;
import tn.esprit.spring.registration.token.ConfirmationToken;
import tn.esprit.spring.repository.CommentairePubRepository;
import tn.esprit.spring.repository.LikesRepository;
import tn.esprit.spring.repository.PublicationRepository;
@Service
public class CommentairePubService implements ICommentairePubService {

	@Autowired
	PublicationRepository pubrepo;
	@Autowired
	CommentairePubRepository comrepo;
	@Autowired
	UserRepository userrepo;
	@Autowired
	LikesRepository likrepo;
	
	
	
	@Override
	public String AjouterCommentare(CommentairePub com, Long idPub, String email) {
		
		Publication p=pubrepo.findById(idPub).orElse(null);
		
		User u = userrepo.findByEmail(email).orElse(null);
		 boolean userExists = userrepo
	                .findByEmail(u.getEmail())
	                .isPresent();
		 boolean pubexist = pubrepo.findById(idPub).isPresent();
	
		if (pubexist){
		
		  if (userExists) {
			  List<String> badwords=new ArrayList<>();
				badwords.add("bad");
				badwords.add("badwords");
				badwords.add("bads");
				String motPost[]=com.getComment().split(" ");
				String comm="";
				 
			for(String mots:motPost){

				
					if (badwords.contains(mots)){
					    mots="***";
						comm=comm+" "+mots;
						com.setComment(comm);
						com.setCreatedAt(LocalDateTime.now());
						p.setCommentrs(p.getCommentrs()+1);
						com.setPublications(p);
						com.setCommented_By(email);
						comrepo.save(com);
						return comm;
					}
				else
					comm=comm+" "+mots;}
			 com.setComment(comm);
			com.setCreatedAt(LocalDateTime.now());
			p.setCommentrs(p.getCommentrs()+1);
			com.setPublications(p);
			com.setCommented_By(email);
			comrepo.save(com);
			 
			 
			 return comm;
			  
	
		
		
		
		
		
		
		
		
	}else{
		throw new IllegalStateException("email already taken");
	}}
		return "okey";
	}
	 
	



	@Override
	public List<CommentairePub> getCommentairePub() {
		return comrepo.findAll();
	}



	@Override
	public void deleteCom(Long id) {
		CommentairePub p = comrepo.findById(id).orElse(null);
		comrepo.delete(p);
		}



	@Override
	public void updateCom(CommentairePub comP) {
		
		comrepo.save(comP);
	}






	@Override
	public int nbreLikeParCmnt(Long id) {
		
	
		return comrepo.egwfwwef(id);

	
	
	}
	

	@Override
	public List<CommentairePub> dasd(String title,String description,TypePub type,String users) {
		
	
		return comrepo.suggestedCom(title, description, type,users);

	
	
	}





	@Override
	public List<CommentairePub> Lisaa(Long pub) {
		return comrepo.Lisaaa(pub);
	}
	



	




}
