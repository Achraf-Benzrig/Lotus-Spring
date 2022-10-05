package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.repository.CagnotteRepository;
import tn.esprit.spring.repository.EvenementRepository;
import tn.esprit.spring.repository.ReservationRepository;

@Service
public class EvenementService implements IEvenementService  {

	
	
	@Autowired
	CagnotteRepository cagRepository;
	
	
	
	@Autowired
	EvenementRepository eventRepository ;
	
	@Autowired
	ReservationRepository reservationRepository ;
	
	
	
	
	
	
	
	
	
	@Override
	public Evenement addEvent(Evenement event,Reservation res,Date dd , Date df){
		Cagnotte cag = new Cagnotte();
		cag.setDateDebut(dd);
		cag.setDateFin(df);
		event.setCagnotte(cag);
		event.setReservation(res);
		eventRepository.save(event);
		
		return event;
		
		
	}
	
	
	@Override
	public Evenement UpdateEvent(Long  idevent,Evenement event,Reservation res,Date dd , Date df){
		Evenement e =  eventRepository.findById(idevent).orElse(null);
		
		e.setAffiche(event.getAffiche());		
		e.setTitre(event.getTitre());
		e.setLieux(event.getLieux());
		e.setDateEvenement(event.getDateEvenement());
		e.setDescription(event.getDescription());
		e.setReservation(res);
		e.getCagnotte().setDateDebut(dd);
		e.getCagnotte().setDateFin(df);
		
		eventRepository.save(e);
		
		return event;
		
		
	}
	
	@Override
	public String deleteevent(Long  idevent){
		Evenement e =  eventRepository.findById(idevent).orElse(null);
		reservationRepository.delete(e.getReservation());
		cagRepository.delete(e.getCagnotte());

		eventRepository.delete(e);
		
		return " Event deleted "+ idevent;

	}
	
	@Override
	public Evenement getevent(Long  idevent){
		Evenement e =  eventRepository.findById(idevent).orElse(null);
		
		return e;

	}
	
	@Override
	public List<Evenement> getallevent(Long  idevent){
		List<Evenement> e =  eventRepository.findAll();
		
		return e;

	}
	
	
	
}
