package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Emojis;
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
import tn.esprit.spring.repository.EmojisRepository;
import tn.esprit.spring.repository.LikesRepository;
@Service
public class EmojisService implements IEmojisService {
	@Autowired
	CommentairePubRepository comrepo;
	@Autowired
	UserRepository userrepo;
	@Autowired
	EmojisRepository emorepo;
	
	@Override
	public void AjouterEmo(Emojis emoj, Long idCo, String email) {
		User u = userrepo.findByEmail(email).orElse(null);
		boolean userExists = userrepo
                .findByEmail(u.getEmail())
                .isPresent();
		boolean emojisbyExists = emorepo
                .findByEmojisBy(email)
                .isPresent();
		


		  if (userExists) {
			  
			  if(emojisbyExists){}
			  else{
				
			  CommentairePub p = comrepo.findById(idCo).orElse(null);
		  
			
			Emojis L = emorepo.save(emoj);
		L.setEmojisBy(email);
		L.setCommentairesPubsss(p);
		emorepo.save(L);
}}
		  else{
		



	
		  }
	}
	@Override
	public List<Emojis> getEmojis() {
		return emorepo.findAll();
		
	}

	@Override
	public void deleteEmojis(Long id) {
		Emojis p = emorepo.findById(id).orElse(null);
	emorepo.delete(p);	
	}

}
