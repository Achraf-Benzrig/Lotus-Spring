package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Ressource;

public interface RessourcesService {
	void ReserverRessources(Ressource ressources);
	Ressource updateRessources(Ressource ressources);
	void deleteRessource (Long idRessource);
	List<Ressource> AfficherAllRessource();
	Ressource afficherRessourceId(Long idRessource);
	public void affecterRessourceFormation(Long idRessource,Long idFormation);

}
