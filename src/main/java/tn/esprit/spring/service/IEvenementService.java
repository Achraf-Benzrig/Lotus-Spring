package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.entity.Reservation;

public interface IEvenementService {


	Evenement addEvent(Evenement event);

	Evenement UpdateEvent(Long idevent, Evenement event, Reservation res, Date dd, Date df);

	String deleteevent(Long idevent);

	Evenement getevent(Long idevent);

	List<Evenement> getallevent();

	Evenement addEventonly(Evenement event);

	Evenement effectuer(Long idevent, Long idres, Long idcag);

	List<Evenement> getEventByAdress(String adresse);

	void addParticipant(Long idEvent, String email);

	void deleteEvent(Long idEvent);
	

}
