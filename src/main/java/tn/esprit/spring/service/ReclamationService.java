package tn.esprit.spring.service;

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
	UserRepository userRepository ;
	@Override
	@Transactional
	public void addReclamation(Reclamation r) {
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

}
