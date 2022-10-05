package tn.esprit.spring.service;

import java.time.LocalDateTime;

public interface INotificationUserService {

	public void sendGlobalNotification(String description);
	public void sendPrivateNotification(String description,String email);
	void readNotificationUser(Long id);
	public void deleteNotificationUser(Long id);
	
}
