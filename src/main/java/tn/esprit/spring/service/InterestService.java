package tn.esprit.spring.service;

import tn.esprit.spring.entity.Interest;

public interface InterestService {
	public void addUserInterest(Long interestId,String email);
	public void removeUserInterest(Long interestId,String uname);
	public void addTrainingInterest(Long interestId,Long trainingId);
	public void removeTrainingInterest(Long trainingId);
	public Interest addInterest( Interest  interest);
	

}
