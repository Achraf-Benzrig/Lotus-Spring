package tn.esprit.spring.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.service.IReservationService;

@RestController
@Api(tags = "Reservation management")
@RequestMapping("/reservation")
@CrossOrigin(origins = "http://localhost:8089")
public class ReservationController {
	
	
	@Autowired
	IReservationService reservationService;

	
	
	// http://localhost:8089/addreservation//
			@PostMapping("/addreservation/")
			@ResponseBody
			public Reservation addreservation(@RequestBody Reservation res)
			{
				return	reservationService.addReservation(res);
				
			}
}
