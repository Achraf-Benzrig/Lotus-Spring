package tn.esprit.spring.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Calendrier;
import tn.esprit.spring.entity.RendezVous;
import tn.esprit.spring.repository.CalendrierRepository;
import tn.esprit.spring.repository.RendezVousRepsitory;

@Service
public class RendezVousService implements IRendezVousService{

	@Autowired
	RendezVousRepsitory rendezVousRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CalendrierRepository calendrRepo;
	@Autowired
	ICalendrierServices calendrService;
	@Override
	public String addRendezVous(RendezVous r ,Long userId) {
		byte[] array = new byte[7]; 
	    new Random().nextBytes(array);
		String code = new String(array, Charset.forName("UTF-8"));
		r.setCode(code); 
		User user = userRepo.findById(userId).orElse(null);
		r.setUser(user);
		if (verifRendezVousDateInput(r)==true && verifExistCalendr(r)==true) {
		rendezVousRepo.save(r);
		return "t3addet";}
		else return "verif date input";
	}
	@Override
	public List<RendezVous> getAllRendezVous() {		
		return (List<RendezVous>) rendezVousRepo.findAll();
	}
	@Override
	public String updateRendezVous(RendezVous r,Long userId) {
		byte[] array = new byte[7]; 
	    new Random().nextBytes(array);
		String code = new String(array, Charset.forName("UTF-8"));
		r.setCode(code); 
		r.setUser(userRepo.getById(userId));
		if (verifRendezVousDateInput(r)==true) {
			rendezVousRepo.save(r);
			return "t3addet";}
			else return "verif date input";
	}
	@Override
	public String deleteRendezVous(Long id) {
		rendezVousRepo.deleteById(id);
		return "Deleted";
	}
	@Override
	public boolean verifRendezVousDateInput(RendezVous r) {
		if(r.getDebut().compareTo(r.getFin())>=0) {return false;}
		else
		return true;
	}
	@Override
	public boolean verifExistCalendrMedcin(RendezVous r) {
		List<Calendrier> listCalendr =calendrService.getAllCalendr();
		for(Calendrier c : listCalendr) {
			if( c.getUser().getAppUserRole().name()=="MEDCIN") {
				if((c.getDebut().compareTo(r.getDebut()) <=0)&& (c.getFin().after(r.getFin()) )) {
					return true;
				}
			}
		}
		
		return false;
	}
	@Override
	public boolean verifExistCalendrLawyer(RendezVous r) {
		List<Calendrier> listCalendr =calendrService.getAllCalendr();
		for(Calendrier c : listCalendr) {
			if( c.getUser().getAppUserRole().name()=="LAWYER") {
				if((c.getDebut().compareTo(r.getDebut()) <=0)&& (c.getFin().after(r.getFin()) )) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean verifExistCalendrPsy(RendezVous r) {
		List<Calendrier> listCalendr =calendrService.getAllCalendr();
		for(Calendrier c : listCalendr) {
			if( c.getUser().getAppUserRole().name()=="PSY") {
				if((c.getDebut().compareTo(r.getDebut()) <=0)&& (c.getFin().after(r.getFin()) )) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean verifExistCalendr(RendezVous r) {
		if(r.getRendezVousType().name()=="AVEC_MEDCIN") {
			return verifExistCalendrMedcin(r);
		}
		else if(r.getRendezVousType().name()=="AVEC_LAWYER") {
			return verifExistCalendrLawyer(r);
		}
		else if(r.getRendezVousType().name()=="AVEC_PSY") {
			return verifExistCalendrPsy(r);
		}
		else
		return false;
	}

}
