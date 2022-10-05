package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Reservation;

public interface IReservationService {

	//Reservation addReservation(Date dr, int maxp, String lieux);

	Reservation Updatereservation(Long idres, Date dr, int maxp, String lieux);

	Reservation Findbyidreservation(Long idres);

	Reservation FindReservationByidEvent(Long idevent);

	String deleteReservation(Long idres);

	List<Reservation> getallres();

	Reservation getres(Long idres);

	Reservation addReservation(Reservation res);

}
