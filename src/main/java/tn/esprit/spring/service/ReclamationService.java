package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.repository.ReclamationRepository;

@Service
public class ReclamationService implements IReclamationService{

	@Autowired
	ReclamationRepository rRepo;
	@Autowired
	UserRepository userRepo ;
	
	@Override
	@Transactional
	public void addReclamation(Reclamation r, Long id) {
		User user = userRepo.findById(id).orElse(null);
		r.setUser(user);
		rRepo.save(r);
	
	}
	@Override
	public List<Reclamation> getAllReclamations() {
		
		return (List<Reclamation>) rRepo.findAll();
	}
	@Override
	public void deleteReclamation(Long id) {
		Reclamation r= rRepo.findById(id).get();
		rRepo.delete(r);
	}
	@Override
	public void marqueTraitee(Long id) {
		Reclamation rec = (Reclamation) rRepo.findById(id).orElse(null)	;
		rec.setTraitee(true);
		rRepo.save(rec);
	}
	@Override
	public List<Reclamation> getReclamationsByUser(Long idUser) {
		List<Reclamation> first = (List<Reclamation>) rRepo.findAll();
		List<Reclamation> second = new ArrayList<Reclamation>();
		for(Reclamation rec : first)
		{
			if(rec.getUser().getId()==idUser)
			{
				second.add(rec);
				
			}
		}
		
		return second;
	}
	@Override
	public List<Reclamation> getReclamationsNonTraitees() {
		List<Reclamation> myList = rRepo.findAllNonTraitees();
		return myList;
	}
	@Override
	public List<Reclamation> getReclamationsTraitees() {
		List<Reclamation> myList = rRepo.findAllTraitees();
		return myList;
	}

}
