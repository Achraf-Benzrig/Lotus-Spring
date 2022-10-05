package tn.esprit.spring.service;

import tn.esprit.spring.User.User;

public interface ISubscriberService {
	public void addSubscriber(String email);
	public void unSubscribe(String email);
	public void extensionSubscribe(String email);
	

}
