package tn.esprit.spring.controller;

import java.util.List;

import javax.validation.Valid;

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

import tn.esprit.spring.entity.Calendrier;
import tn.esprit.spring.service.ICalendrierServices;

@RestController
@RequestMapping("/Calendrier")
public class CalendrierController {
@Autowired
ICalendrierServices calendrService;
@PostMapping("/addCalendrier/{userId}")
@ResponseBody
//localhost:8080/WomenEmpowerment/Calendrier/addCalendrier
public String addCalendrier(@RequestBody  Calendrier c ,@PathVariable("userId") Long userId) {
	calendrService.addCalendrier(c,userId);
	return calendrService.addCalendrier(c,userId);
}
@GetMapping("/getAllCalendrier")
public List<Calendrier> getAllCalendr(){
	return calendrService.getAllCalendr();	
}
@DeleteMapping("/deleteCalenrier/{idCalendr}")
public void deleteCalendr(@PathVariable("idCalendr") Long id) {
	calendrService.deleteCalendr(id);
}
@PutMapping("/updateCalendrier")
@ResponseBody
public String updateCalendr(@RequestBody Calendrier c) {
	calendrService.updateCalendr(c);
	return calendrService.updateCalendr(c);
}
}
