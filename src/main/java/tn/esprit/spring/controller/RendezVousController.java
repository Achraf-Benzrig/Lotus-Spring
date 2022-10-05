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

import tn.esprit.spring.entity.RendezVous;
import tn.esprit.spring.service.IRendezVousService;

@RestController
@RequestMapping("/RendezVous")
public class RendezVousController {
@Autowired
IRendezVousService rendezVousService;
@PostMapping("/add/{userId}")
@ResponseBody
public String addRendezVous(@RequestBody RendezVous r,@PathVariable("userId") Long userId) {
	return rendezVousService.addRendezVous(r,userId);
}
@GetMapping("/getAll")
public List<RendezVous> getAllRendezVous(){
	return rendezVousService.getAllRendezVous();
}
@PutMapping("/update/{userId}")
@ResponseBody
public String updateRendezVous(@RequestBody RendezVous r, @PathVariable("userId") Long userId) {
	return rendezVousService.updateRendezVous(r,userId);
}
@DeleteMapping("/delete/{idR}")
public String deletRendezVous(@PathVariable("idR") Long id) {
	
	return rendezVousService.deleteRendezVous(id);
}
}
