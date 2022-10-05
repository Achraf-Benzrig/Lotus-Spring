package tn.esprit.spring.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vdurmont.emoji.EmojiParser;

import tn.esprit.spring.Response.Com;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.User.UserRole;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class PublicationService implements IPublicationService {
	
	private static final Com Com = null;
	@Autowired
	PublicationRepository pubrepo;
	@Autowired
	UserRepository userrepo;
	
	@Override
	public Publication retrievePostById(Long idPost) {
		
		return pubrepo.findById(idPost).orElse(null);
	}

	@Override
	public Com AjouterPub(Publication pub, String email) {
	
	 	User u = userrepo.findByEmail(email).orElse(null);
		
		  
		pub.setCreatedAt(LocalDateTime.now());
	Publication p = pubrepo.save(pub);
		
	
		
		p.setUsers(u);
		 pubrepo.save(p);
		 
		 return Com;
	 }
	
	@Override
	public List<Publication> search(String keyword){
        if (keyword != null) {
            return pubrepo.search(keyword);
        }
        return (List<Publication>) pubrepo.findAll();
    }
		
	@Override
	public List<Publication> getPublications() {
	
	
			return pubrepo.findAll();
		  
		
		
		
	}
	


	@Override
	public void deletePub(Long id) {
		Publication p = pubrepo.findById(id).orElse(null);
		pubrepo.delete(p);
		
	}

	@Override
	public void updatePub(Publication pub,String email) {
		User u = userrepo.findByEmail(email).orElse(null);
		pub.setCreatedAt(LocalDateTime.now());
		pub.setUsers(u);
		pub.setUsers(u);
		pubrepo.save(pub);
	
		
	}
	
	@Override
	public List<Publication> suggpub(String  description,String  companyname,String  assoc,UserRole  role,int age) {
		// TODO Auto-generated method stub
		return pubrepo.suggestedPub(description, companyname, assoc, role, age);
	}
	

	@Override
	public Publication getPubID(Long id) {
		// TODO Auto-generated method stub
		return pubrepo.findById(id).get();
	}

	

	


	

}
