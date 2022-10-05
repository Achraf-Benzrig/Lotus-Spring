package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping("/add")
	@ResponseBody
	//localhost:8080/WomenEmpowerment/Reclamation/addReclamation/
	public void addReclamation(@RequestBody Reclamation r) {
		rService.addReclamation(r);
	}
	@GetMapping("/getAll")
	
	public List<Reclamation> getAllReclamations(){
		return rService.getAllReclamations();
	}
	@DeleteMapping("/delete/{idReclamation}")
	public void deleteReclamation(@PathVariable("idReclamation") Long id) {
		rService.deleteReclamation(id);
	}
}
