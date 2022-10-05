package tn.esprit.spring.service;


import tn.esprit.spring.User.User;

import java.util.List;

import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.Subscriber;


public interface ISubscriberService {
	public List<Subscriber> getSubscribers();
	public void addSubscriber(String email);
	public void unSubscribe(String email);
	public void extensionSubscribe(String email);

	List<Object[]> countSubscriberByMonth();
	

}
