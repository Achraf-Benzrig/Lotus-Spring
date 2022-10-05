package tn.esprit.spring.controller;

import java.util.List;

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

import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.service.IReclamationService;

@RestController
@RequestMapping("/Reclamation")
public class ReclamationController {
	
	@Autowired
	IReclamationService rService;
	@PostMapping("/add/{userId}")
	@ResponseBody
	//localhost:8080/WomenEmpowerment/Reclamation/add/
	public void addReclamation(@RequestBody Reclamation r,@PathVariable("userId") Long id) {
		rService.addReclamation(r,id);
	}
	@GetMapping("/getAll")
	
	public List<Reclamation> getAllReclamations(){
		return rService.getAllReclamations();
	}
	@GetMapping("/getReclamationsTraitees")
	@ResponseBody
	public List<Reclamation> getReclamationsTraitees() {
		return rService.getReclamationsTraitees();
		
	}
	
	@GetMapping("/getReclamationsNonTraitees")
	@ResponseBody
	public List<Reclamation> getReclamationsNonTraitees() {
		return rService.getReclamationsNonTraitees();
		
	}
	@GetMapping("/getReclamationsByClient/{idUser}")
	@ResponseBody
	public List<Reclamation> getReclamationsByClient(@PathVariable("idUser") Long idUser) {
		return rService.getReclamationsByUser(idUser);
		
	}
	@PutMapping("/marquerTraitee/{idReclamation}")
	@ResponseBody
	public void marquerTraitee(@PathVariable("idReclamation") Long idReclamation)
	{
		rService.marqueTraitee(idReclamation);
	}
	@DeleteMapping("/delete/{idReclamation}")
	public void deleteReclamation(@PathVariable("idReclamation") Long id) {
		rService.deleteReclamation(id);
	}
}
