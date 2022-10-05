package tn.esprit.spring.service;


import java.util.List;

import tn.esprit.spring.entity.Training;

public interface TrainingService {
	
	 void ajouterFormation(Training training,String email);
	Training updateTraining(Training t,String email);
	void deleteTraining(Long id);

	List<Training> retrieveAllTrainings();
	
	Training retrieveTraining(Long id);
	public void affecterUserFormation(Long idUser,Long idFormation);
	public List<Training> suggestTraining(String uname);
	//List<Facture> getFacturesByClient(Long clientId);
	public List<Training> searchTraining(String text);
}

