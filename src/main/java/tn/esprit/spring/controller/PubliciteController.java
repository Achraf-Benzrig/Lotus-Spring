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

import tn.esprit.spring.entity.PopulationCible;
import tn.esprit.spring.entity.Publicite;
import tn.esprit.spring.repository.PubliciteRepository;
import tn.esprit.spring.service.IPubliciteService;

@RestController
@RequestMapping("/Publicite")
public class PubliciteController {
	@Autowired
	IPubliciteService pubService;
	@PostMapping("/add")
	@ResponseBody
	public String addPublicite(@RequestBody Publicite p ) {
		return pubService.addPublicite(p);
	}
	@GetMapping("/getAll")
	public List<Publicite>getAllPub( ) {
		return pubService.getAllPublicite();
	}
	@DeleteMapping("/delete/{idPub}")
	public void deletePub(@PathVariable("idPub") Long id) {
		pubService.deletePub(id);
	}
	@GetMapping("/maxGain/{idPub}")
	public String maxGain(@PathVariable("idPub") Long id) {
		return pubService.maxGain(id);
	}
}
