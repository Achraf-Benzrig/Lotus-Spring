package tn.esprit.spring.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.service.ICagnotteService;



@RestController
@Api(tags = "Cagnotte management")
@RequestMapping("/cagnotte")
@CrossOrigin(origins = "http://localhost:8089")
public class CagnotteController {

	@Autowired
	ICagnotteService cagnottesService;
	
	
	// http://localhost:8089/addreservation//
				@PostMapping("/addcagnotte")
				@ResponseBody
				public Cagnotte addcagnotte(@RequestBody Cagnotte cag)
				{
					return	cagnottesService.addcagnotte(cag);
					
				}
	
	
	
	
}
