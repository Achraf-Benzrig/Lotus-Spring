package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Rating;
import tn.esprit.spring.service.IPublicationService;
import tn.esprit.spring.service.IRatingService;
@RestController
@RequestMapping("/rating")
public class RatingController {






	@Autowired
	IRatingService ratserv;

	@PostMapping("/add/{idPub}/{email}")
	@ResponseBody
	public void ajoutercategorie(@ RequestBody  Rating rat,@PathVariable("idPub") Long idPub,@PathVariable("email") String email){

		
		ratserv.AjouterRat(rat, idPub, email);
		
		
	}
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void deleteUser(@PathVariable("id") Long id){
		ratserv.deleteRate(id);
		
	}
	
	
	//http://localhost:8089/WomenEmpowerment/publication/update
	@PutMapping("/update/{idPub}/{email}")
	@ResponseBody
	public void updateUser(@RequestBody Rating rat,@PathVariable("idPub") Long idPub,@PathVariable("email") String email) {
		ratserv.updateRate(rat, idPub, email);
	
	}
	
	@GetMapping("/rateavg/{pub}")
	@ResponseBody
	public float rateavg(@PathVariable("pub") Long pub){
		return ratserv.RateAVG(pub);
		
	}
	
	

}