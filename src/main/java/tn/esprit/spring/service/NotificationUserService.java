package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.NotificationUser;
import tn.esprit.spring.repository.NotificationUserRepository;



@Service
public class NotificationUserService implements INotificationUserService{

	@Autowired
	NotificationUserRepository myRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void sendGlobalNotification(String description) {
		List<User> listUser = userRepository.findAll();
		for (User user : listUser) {
			NotificationUser notification = new NotificationUser();
			notification.setDescription(description);
			notification.setCreatedAt(LocalDateTime.now());
			notification.setUser(user);
			myRepository.save(notification);
			
		}
		
	}

	@Override
	public void sendPrivateNotification(String description,String email) {
		User u = userRepository.findByEmail(email).orElse(null);
		NotificationUser notification = new NotificationUser();
		notification.setDescription(description);
		notification.setCreatedAt(LocalDateTime.now());
		notification.setUser(u);
		myRepository.save(notification);
		
		
	}

	@Override
	public void readNotificationUser(Long id) {
		myRepository.readNotificationUser(LocalDateTime.now(), id);
		
	}

	@Override
	public void deleteNotificationUser(Long id) {
		myRepository.deleteById(id);
		
	}
	


    



}
