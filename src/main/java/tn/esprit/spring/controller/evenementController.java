package tn.esprit.spring.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.service.IEvenementService;

@RestController
@Api(tags = "Event management")
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:8089")
public class evenementController {
	
	
	@Autowired
	IEvenementService EventService;
	
	
	// http://localhost:8089/WomenEmpowerment/reporting/addReport/
	@PostMapping("/addevent/{dd}/{df}")
	@ResponseBody
	public void addEvent(@PathVariable("dd") Date dd,@PathVariable("df") Date df){
		return	EventService.addEvent(event,res, dd ,  df);
		
	}
	
	
	
	
	

}
