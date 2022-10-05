package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.repository.CagnotteRepository;
import tn.esprit.spring.repository.EvenementRepository;



@Service
public class CagnotteService implements ICagnotteService {
	
	
	@Autowired
	CagnotteRepository cagRepository;
	
	@Autowired
	EvenementRepository eventRepository ;
	
	
	@Override
	public Cagnotte addcagnotte(Cagnotte cag){
		
		cagRepository.save(cag);
		return cag;
		
	}
	@Override
	public Cagnotte addcagnottetoevent(Long  idevent,Date dd , Date df){
		
		Cagnotte cag = new Cagnotte();		
		Evenement e =  eventRepository.findById(idevent).orElse(null);
		cag.setEvenement(e);
		cagRepository.save(cag);
		return cag;
		
	}

	@Override
	public Cagnotte Updatecagnotte(Long  idcag,float valeur,Date dd , Date df){
		
		Cagnotte cag = cagRepository.findById(idcag).orElse(null);
		cag.setValeur(valeur);
		cag.setDateDebut(dd);
		cag.setDateFin(df);
		cagRepository.save(cag);
		return cag;
		
	}
	
	@Override
	public Cagnotte Findbyidcagnotte(Long  idcag){
		
		Cagnotte cag = cagRepository.findById(idcag).orElse(null);
		return cag;
		
	}
	
	@Override
	public Cagnotte Findbyidevent(Long  idevent){
		
		Evenement e =  eventRepository.findById(idevent).orElse(null);
	
		return e.getCagnotte();
		
	}
	
	@Override
	public String deletecagnotte(Long  idcag){
		Cagnotte cag = cagRepository.findById(idcag).orElse(null);
		cagRepository.delete(cag);
		return "cag deleted succ";
	
		
	}
	
	
	@Override
	public List<Cagnotte> getallcag(){
		return cagRepository.findAll();
		
	}
	
	@Override
	public Cagnotte getcag(Long  idcag){
		return cagRepository.findById(idcag).orElse(null);
		
	}
}
