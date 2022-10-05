package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Interest;
import tn.esprit.spring.service.InterestService;


@RestController
@RequestMapping("/Interest")
public class InterestController {
	
	@Autowired
	InterestService interestService;
	
	
	@PostMapping("/add-interest")
	@ResponseBody
	public Interest addInterest(@ RequestBody  Interest  interest) {
		return interestService.addInterest(interest);
	}
	
	
	@PostMapping("/addUserInterest")
	public void addUserInterst(@RequestParam("interestId") long interestId,@RequestParam String email ) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		interestService.addUserInterest(interestId,email);
	}
	
	
	@PostMapping("/removeUserInterest")
	public void removeUserInterst(@RequestParam("interestId") long interestId ) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uname;
		if (principal instanceof UserDetails) {
			   uname = ((UserDetails)principal).getUsername();
		} else {
			   uname = principal.toString();
		}
		interestService.removeUserInterest(interestId, uname);
	}
	
	
	
	@PostMapping("/addEventInterest")
	public void addTrainingInterest(@RequestParam("interestId") long interestId, @RequestParam("trainingId") long trainingId) {
		interestService.addTrainingInterest(interestId, trainingId);
	}
	@PostMapping("/removeEventInterest")
	public void removeTrainingInterest(@RequestParam("eventId") long trainingId) {
		interestService.removeTrainingInterest(trainingId);
	}
	
	
	
	
	
}
