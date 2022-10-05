package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Calendrier;
import tn.esprit.spring.repository.CalendrierRepository;

@Service
public class CalendrierServices implements ICalendrierServices {

	@Autowired
	CalendrierRepository cRepo;
	@Autowired
	UserRepository userRepo;
	
	@Override
	public String addCalendrier(Calendrier c, Long userId) {
		User user = userRepo.findById(userId).orElse(null);
		if(verifCalendr(c) == true) {
			c.setUser(user);
			cRepo.save(c);
		return "successfuly added";
		}
		else
			return "error";
	}
	@Override
	public List<Calendrier> getAllCalendr() {
		
		return (List<Calendrier>) cRepo.findAll();
	}
	@Override
	public void deleteCalendr(Long id) {
		cRepo.deleteById(id);
	}
	@Override
	public String updateCalendr(Calendrier c) {	
		if(verifCalendr(c) == true) {
			cRepo.save(c);
			return "successfuly updated";
			}
			else
				return "error";
		
	}

	@Override
	public boolean verifCalendr(Calendrier c) {
		if(c.getDebut().compareTo(c.getFin())>=0) {return false;}
		else
		return true;
	}

}
