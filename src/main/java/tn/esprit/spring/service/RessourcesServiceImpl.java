package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Ressource;
import tn.esprit.spring.entity.Training;
import tn.esprit.spring.repository.RessourceRepository;
import tn.esprit.spring.repository.TrainingRepository;


@Service

public class RessourcesServiceImpl implements RessourcesService{
	
	
	@Autowired
	private RessourceRepository ressourceRepo;
	
	@Autowired
	private TrainingRepository trainRepo;

	
	public void ReserverRessources(Ressource ressources) {
		ressourceRepo.save(ressources);
		
	}


	@Override
	public Ressource updateRessources(Ressource ressources) {
		
		return ressourceRepo.save(ressources);
	}


	@Override
	public void deleteRessource(Long idRessource) {
		
		ressourceRepo.deleteById(idRessource);
	}


	@Override
	public List<Ressource> AfficherAllRessource() {
		
		return (List<Ressource>)ressourceRepo.findAll();
	}


	@Override
	public Ressource afficherRessourceId(Long idRessource) {
		
		return ressourceRepo.findById(idRessource).get();
	}


	@Override
	public void affecterRessourceFormation(Long idRessource, Long idFormation) {
		Training training=trainRepo.findById(idFormation).get();
		Ressource ressource =ressourceRepo.findById(idRessource).get();
		ressource.setTrainings(training);
		ressourceRepo.save(ressource);
		
		
	}

	
	

}
