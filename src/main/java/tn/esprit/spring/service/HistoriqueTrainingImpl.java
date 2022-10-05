package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.HistoriqueTraining;
import tn.esprit.spring.entity.Training;
import tn.esprit.spring.repository.HistoriqueTrainingRepository;

@Service
public class HistoriqueTrainingImpl implements HistoriqueTrainingService {
	
	@Autowired
	HistoriqueTrainingRepository historiqueTrain;

	@Override
	public void ajouterHistorique(HistoriqueTraining historique) {
		historiqueTrain.save(historique);
		
	}

	@Override
	public HistoriqueTraining updateHistorique(HistoriqueTraining t) {
		return historiqueTrain.save(t);
		 
	}

	@Override
	public void deleteTraining(Long id) {
	 historiqueTrain.deleteById(id);
		
	}

	@Override
	public List<HistoriqueTraining> getHistoriqueByUser(User u) {
		
		return historiqueTrain.findByUser(u);
	}

}
