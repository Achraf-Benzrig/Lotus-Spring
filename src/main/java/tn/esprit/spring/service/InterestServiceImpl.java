package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Interest;
import tn.esprit.spring.entity.Training;
import tn.esprit.spring.repository.InterestRepository;
import tn.esprit.spring.repository.TrainingRepository;
@Service
public class InterestServiceImpl implements  InterestService{
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private InterestRepository ir;
	
	@Autowired
	private TrainingRepository tr;
	
	

	@Override
	public void addUserInterest(Long interestId, String email) {
		 User user= (User) ur.findByEmail(email).orElse(null);
		Interest interest=ir.findById(interestId).orElse(null);
		user.getInterests().add(interest);
		ur.save(user);
		
	}

	@Override
	public void removeUserInterest(Long interestId, String uname) {
		User user= (User)ur.findByfirstNameContains(uname);
		Interest interest=ir.findById(interestId).orElse(null);
		user.getInterests().remove(interest);
		ur.save(user);
		
		
	}

	@Override
	public void addTrainingInterest(Long interestId, Long trainingId) {
		 Training training= tr.findById(trainingId).orElse(null);
		 Interest interest=ir.findById(interestId).orElse(null);
		 training.setIntereset(interest);
			tr.save(training);
		
	}

	@Override
	public void removeTrainingInterest(Long trainingId) {
		
		Training training= tr.findById(trainingId).orElse(null);
		
		 training.setIntereset(null);
			tr.save(training);
	}

	@Override
	public Interest addInterest(Interest interest) {
		return ir.save(interest);
	}
	 

}
