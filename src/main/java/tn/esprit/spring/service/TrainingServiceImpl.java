package tn.esprit.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Interest;
import tn.esprit.spring.entity.Ressource;
import tn.esprit.spring.entity.Training;
import tn.esprit.spring.repository.TrainingRepository;


@Service
public class TrainingServiceImpl implements TrainingService {
	
	
	
	@Autowired
	private TrainingRepository trainRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JavaMailSender mailsender;

	
	@Override
	public void ajouterFormation(Training training,String email) {
		
		User u=userRepo.findByEmail(email).orElse(null);
		 List<Training> t=(List<Training>) trainRepo.findAll();
		 
		 int i=0;
		 for (Training training2 : t) {
			 System.out.println(training2.getFormateur());
			if (training2.getFormateur().equals(email)){
				i=i+1;
				System.out.println(i);
			}
		}
		
		if (i>=2)
			
		{
			return;
		}
		else 
		{
			
			
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("faroukgasaraa@gmail.com");
		message.setTo("loujein.hragua@esprit.tn");
		message.setSubject("Nouvelle formation\n");
		message.setText("Hi "+userRepo.findByEmail(email).get().getFirstName() +" Welcom to your Training " +training.getTitle());
		System.out.println("success");
		mailsender.send(message);
		
	
		
			training.setFormateur(email);
			trainRepo.save(training);	
		}
		 
	}

	@Override
	public Training updateTraining(Training t,String email) {
		
		return trainRepo.save(t);
				}

	@Override
	public void deleteTraining(Long id) {
		
		trainRepo.deleteById(id);
	}

	@Override
	public List<Training> retrieveAllTrainings() {
		
		return (List<Training>)trainRepo.findAll();
	}

	@Override
	public Training retrieveTraining(Long id) {
		return trainRepo.findById(id).get();
	}

	@Override
	public void affecterUserFormation(Long idUser, Long idFormation) {
		
		//if (User.this.getAvailability().equals(1)) 
				{
			Training training=trainRepo.findById(idFormation).get();
		User user =userRepo.findById(idUser).get();
		
		if(user.getTrainings().size() == 3){
			System.out.println("hi");
			
		}else {
			training.getUsers().add(user);
			System.out.println("hi2");
			trainRepo.save(training);
		}
		
		} 
		//else 
			//System.out.writeln.("impossible");
			
				 }

	@Override
	public List<Training> suggestTraining(String email) {
		User user= userRepo.findByEmail(email).orElse(null);
		List<Interest> userInterests=user.getInterests();
		return trainRepo.suggestedTrainings(userInterests);
	}

	@Override
	public List<Training> searchTraining(String text) {
	
		return trainRepo.findByTitleContaining(text) ;
	}
	
	

	
		
	

	
}
