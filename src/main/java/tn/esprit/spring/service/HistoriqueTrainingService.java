package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.HistoriqueTraining;


public interface HistoriqueTrainingService {
	 void ajouterHistorique(HistoriqueTraining historique);
	 HistoriqueTraining updateHistorique(HistoriqueTraining t);
		void deleteTraining(Long id);
		List<HistoriqueTraining>getHistoriqueByUser(User u);


}
