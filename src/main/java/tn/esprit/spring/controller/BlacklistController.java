package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entity.Blacklist;
import tn.esprit.spring.service.IBlacklistService;

@RestController
@Api(tags = "Blacklist management")
@RequestMapping("/blacklist")
@CrossOrigin(origins = "http://localhost:8089")
public class BlacklistController {
	
	@Autowired
	IBlacklistService blacklistService;
	// http://localhost:8089/WomenEmpowerment/blacklist/addUserToBlacklist/
	@PostMapping("/addUserToBlacklist/{email}")
	@ResponseBody
	public void addUserToBlacklist(@PathVariable("email") String email){
		blacklistService.addUserToBlacklist(email);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/blacklist/deleteUserFromBlacklist/
	@DeleteMapping("/deleteUserFromBlacklist/{id}")
	@ResponseBody
	public void deleteUserFromBlacklist(@PathVariable("id") Long id){
		blacklistService.deleteUserFromBlacklist(id);
	}
	
	// http://localhost:8089/WomenEmpowerment/blacklist/getAllBlacklist
	@GetMapping("/getAllBlacklist")
	@ResponseBody
	public List<Blacklist> getAllBlacklist(){
		return blacklistService.getAllBlacklist();
	}

}
