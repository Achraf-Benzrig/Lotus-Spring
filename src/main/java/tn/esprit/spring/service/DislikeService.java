package tn.esprit.spring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdurmont.emoji.EmojiParser;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Dislike;
import tn.esprit.spring.entity.Likes;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.repository.CommentairePubRepository;
import tn.esprit.spring.repository.DislikesRepository;
import tn.esprit.spring.repository.LikesRepository;
@Service
public class DislikeService implements IDislikeService {
	@Autowired
	CommentairePubRepository comrepo;
	@Autowired
	UserRepository userrepo;
	@Autowired
	DislikesRepository dislikrepo;
	
	
	@Override
	public void AjouterDislik(Dislike dislik, Long idCo, String email) {
		User u = userrepo.findByEmail(email).orElse(null);
		boolean userExists = userrepo
                .findByEmail(u.getEmail())
                .isPresent();
		boolean dislikedbyExists = dislikrepo
                .findByDislikedBy(email)
                .isPresent();
		


		  if (userExists) {
			  
			  if(dislikedbyExists){}
			  else{
				
			  CommentairePub p = comrepo.findById(idCo).orElse(null);
		  
			
			Dislike L = dislikrepo.save(dislik);
		L.setDislikedBy(email);
		L.setCommentairesPubss(p);
		dislikrepo.save(L);
}}
		  else{
		



	
		  }
	}
	
	@Override
	public List<Dislike> getDislikes() {
	return dislikrepo.findAll();
	}
	@Override
	public void deleteDislikes(Long id) {
		Dislike dl = dislikrepo.findById(id).orElse(null);
		dislikrepo.delete(dl);
		}
		
		
}
