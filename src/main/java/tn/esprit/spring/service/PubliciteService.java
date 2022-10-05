package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Console;

import tn.esprit.spring.entity.Publicite;
import tn.esprit.spring.entity.Simplex;
import tn.esprit.spring.entity.Simplex.Constraint;
import tn.esprit.spring.entity.Simplex.Modeler;
import tn.esprit.spring.repository.PopulationCibleRepository;
import tn.esprit.spring.repository.PubliciteRepository;
import tn.esprit.spring.entity.PopulationCible;
@Service
public class PubliciteService implements IPubliciteService{

	@Autowired
	PubliciteRepository pubRepo;
	@Autowired
	PopulationCibleRepository popRepo;
	
	Simplex simplexx;
	@Override
	public String addPublicite(Publicite p ) {
		PopulationCible pc = p.getPopulationCible();
		Long idaddedpc = popRepo.save(pc).getId();
		Long idaddeedp = pubRepo.save(p).getId();
		
		
		PopulationCible pcaded= popRepo.findById(idaddedpc).orElse(null);
		Publicite paded = pubRepo.findById(idaddeedp).orElse(null);
		pcaded.setPublicite(paded);
		paded.setPopulationCible(pcaded);	
		popRepo.save(pcaded);
		pubRepo.save(paded);
		
		
		
		return "Publicite aded";
	}

	@Override
	public List<Publicite> getAllPublicite() {
		
		return (List<Publicite>)pubRepo.findAll();
	}

	@Override
	public void deletePub(Long id) {
		pubRepo.deleteById(id);
		
	}
	@Override
	public double testSimplex(Long id) {
		Publicite p = pubRepo.findById(id).orElse(null);
		PopulationCible pc = p.getPopulationCible();
		double[] objectiveFunc = { p.getNbrVuesCible(),p.getNbrVuesFinal() };
	     double[][] constraintLeftSide = {
	     { tarifPubParAge(pc), tarifPubParGender(pc) },
	     { tarifPubParGender(pc), tarifPubParProfession(pc) },
	     {tarifPubParAge(pc),tarifPubParProfession(pc)}};
	    Constraint[] constraintOperator = { Constraint.lessThan,
	       Constraint.lessThan, Constraint.lessThan};
	     double[] constraintRightSide = { tarifPubCaneaux(p),tarifPubCaneaux(p),tarifPubCaneaux(p) };
	   
	     Modeler model = new Modeler(constraintLeftSide, constraintRightSide,
	       constraintOperator, objectiveFunc);
	   
	     Simplex simplex = new Simplex(model.getTableaux(),
	       model.getNumberOfConstraint(),
	       model.getNumberOfOriginalVariable(), simplexx.MAXIMIZE);
	    // double[] x = simplex.primal();
		return  simplex.value();
	}

	@Override
	public double tarifPubCaneaux(Publicite p) {
	 if(p.getCanaux().name()=="FACEBOOK")
		 return 10;
	 if(p.getCanaux().name()=="INSTAGRAM")
		 return 12;
	 if(p.getCanaux().name()=="GOOGLE_ADS")
		 return 15; 
	 else
		return 0;
	}

	@Override
	public double tarifPubParAge(PopulationCible pc) {
		 double coutAge;
		 
			if (pc.getAge()>=18 && pc.getAge() < 26 )
				return coutAge = 4;
			if(  pc.getAge()>=26 && pc.getAge() < 45)
				return coutAge=6;
			if(pc.getAge()>=45)
				return coutAge=5;
			else
		return 0;
	}

	@Override
	public double tarifPubParGender(PopulationCible pc) {
		 double coutGender;
		 if(pc.getGender()=="homme")
			 return	coutGender=7;
			if(pc.getGender()=="femme")
				return	coutGender=5;
			else
		return 1;
	}

	@Override
	public double tarifPubParProfession(PopulationCible pc) {
		double coutP ;
		if(pc.getProfession()=="medcin")
			return	coutP=4;
		if(pc.getProfession()=="lawyer")
			return coutP=3;
		else
		return 0;
	}

	@Override
	public String maxGain(Long id) {
		Publicite p = pubRepo.findById(id).orElse(null);
		double result =testSimplex(id);
		return "Le maximum du Gain de la publicité " +p.getNom() +" est: " +result;
	}
	

}
