package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.PopulationCible;
import tn.esprit.spring.entity.Publicite;


public interface IPubliciteService {
	public String addPublicite(Publicite p);
	 public List<Publicite> getAllPublicite();
	 public void deletePub(Long id);
	 public double testSimplex(Long id) ;
	 public double tarifPubCaneaux(Publicite p);
	 public double tarifPubParAge(PopulationCible pc);
	 public double tarifPubParGender(PopulationCible pc);
	 public double tarifPubParProfession(PopulationCible pc);
	 public String maxGain(Long id);
}
