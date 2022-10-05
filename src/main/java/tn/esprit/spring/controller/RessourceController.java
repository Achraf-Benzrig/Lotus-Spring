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

import tn.esprit.spring.entity.Ressource;
import tn.esprit.spring.service.RessourcesService;

@RestController
@RequestMapping("/Ressource")
public class RessourceController {
	
	
	@Autowired 
	RessourcesService ressourceService;
	
	//http://localhost:8089/WomenEmpowerment/Ressource/Add-Ressource
	
	
	@PostMapping("/Add-Ressource")
	@ResponseBody
	public void ReserverRessources(@RequestBody Ressource ressources)
	{
		ressourceService.ReserverRessources(ressources);	
	}
	
	//http://localhost:8089/WomenEmpowerment/Ressource/update-Ressources
	@PutMapping("/update-Ressources")
	@ResponseBody
	public Ressource updateRessources(@RequestBody Ressource ressources)
	{
		return ressourceService.updateRessources(ressources);
		
	}
	
	//http://localhost:8089/WomenEmpowerment/Ressource/delete-Ressource/{Ressource-id}
	@DeleteMapping("/delete-Ressource/{Ressource-id}")
	@ResponseBody
	public void deleteRessource(@PathVariable("Ressource-id") Long idRessource)
	{
		ressourceService.deleteRessource(idRessource);
	}
	//http://localhost:8089/WomenEmpowerment/Ressource/afficher-Ressource
	@GetMapping("/afficher-Ressource")
	@ResponseBody
	public List<Ressource> AfficherAllRessource()
	{
		List<Ressource> listRessource=ressourceService.AfficherAllRessource();
		return listRessource;
	}
	
	
	
	//http://localhost:8089/WomenEmpowerment/Ressource/afficher-RessourceId/{Ressource-id}
		@GetMapping("/afficher-RessourceId/{Ressource-id}")
		@ResponseBody
		public Ressource afficherRessourceId(@PathVariable("Ressource-id") Long idRessource)
		{
			return ressourceService.afficherRessourceId(idRessource);
		}
		//http://localhost:8089/WomenEmpowerment/Ressource/affecterRessourceFormation/{idRessource}/{idFormation}
				@PostMapping("/affecterRessourceFormation/{idRessource}/{idFormation}")
				@ResponseBody
				public void  affecterRessourceFormation(@PathVariable("idRessource") Long idRessource,@PathVariable("idFormation") Long idFormation)
				{
					//int nbx=2;
					//if (nbx>2)
					{
						ressourceService.affecterRessourceFormation(idRessource,idFormation);
					}
				
					
						
					 
				}
			
	

}
