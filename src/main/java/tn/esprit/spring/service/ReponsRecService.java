package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.entity.ReponseRec;
import tn.esprit.spring.repository.ReclamationRepository;
import tn.esprit.spring.repository.ReponseRecRepository;

@Service
public class ReponsRecService implements IReponseRecService{

	@Autowired
	ReponseRecRepository reponseRepo;
	@Autowired
	ReclamationRepository recRepo;
	@Autowired
	IReclamationService recServ;
	@Override
	public String addReponse(ReponseRec reponse, Long id) {
		Reclamation rec = recRepo.findById(id).get();	
		reponse.setReclamation(rec);
		reponseRepo.save(reponse);
		rec.setReponseReclamation(reponse);
		recServ.marqueTraitee(id);
		recRepo.save(rec);
		return "Selket";
	}
	@Override
	public long getTempsAttenteReclamation(Long idReclamation) {
		Reclamation rec = (Reclamation) recRepo.findById(idReclamation).orElse(null);
		
		long duree = Math.abs(rec.getReponseReclamation().getDateReponse().getTime()-rec.getDateReclamation().getTime());
		return duree;
	}
	@Override
	public long getTempsAttenteMoyenReclamation() {
		long total=0;
		List<Reclamation> myList = recRepo.findAllTraitees();
		for(Reclamation rec : myList)
		{
			total+= Math.abs(rec.getReponseReclamation().getDateReponse().getTime()-rec.getDateReclamation().getTime());
		}
		return total/myList.size();
	
	}
	@Override
	public  List<String> suggestion( Long idRec) {
		Reclamation rec = recRepo.findById(idRec).orElse(null);
		return reponseRepo.reponseSuggestion(rec.getType());
	}

}
