package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.entity.Reservation;

public interface IEvenementService {


	Evenement addEvent(Evenement event, Reservation res, Date dd, Date df);

	Evenement UpdateEvent(Long idevent, Evenement event, Reservation res, Date dd, Date df);

	String deleteevent(Long idevent);

	Evenement getevent(Long idevent);

	List<Evenement> getallevent(Long idevent);
	

}
