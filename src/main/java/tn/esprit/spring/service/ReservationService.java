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
public class ReservationService implements IReservationService {
	
	
	
	@Autowired
	ReservationRepository reservationRepository ;
	
	
	@Autowired
	CagnotteRepository cagRepository;
	
	@Autowired
	EvenementRepository eventRepository ;
	
	@Override
	public Reservation addReservation(Reservation res){
		
		
		reservationRepository.save(res);
		return res;
		
	}

	@Override
	public Reservation Updatereservation(Long  idres,Date dr , int maxp ,String lieux){
		
		Reservation res = reservationRepository.findById(idres).orElse(null);
		res.setDateReservation(dr);
		res.setLieuxReservation(lieux);
		res.setMaxParticipants(maxp);
		reservationRepository.save(res);
		
		return res;
		
	}
	
	@Override
	public Reservation Findbyidreservation(Long  idres){
		
		
		return reservationRepository.findById(idres).orElse(null);
		
	}
	
	@Override
	public Reservation FindReservationByidEvent(Long  idevent){
		
		Evenement e =  eventRepository.findById(idevent).orElse(null);
	
		return e.getReservation();
		
	}
	
	@Override
	public String deleteReservation(Long  idres){
		Reservation res = reservationRepository.findById(idres).orElse(null);
		reservationRepository.delete(res);
		return "reservation deleted succ";
	
		
	}
	
	
	@Override
	public List<Reservation> getallres(){
		return reservationRepository.findAll();
		
	}
	
	@Override
	public Reservation getres(Long  idres){
		return reservationRepository.findById(idres).orElse(null);
		
	}

}
