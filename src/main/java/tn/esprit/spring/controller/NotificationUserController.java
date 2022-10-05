package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.service.INotificationUserService;

@RestController
@Api(tags = "NotificationUser management")
@RequestMapping("/notificationuser")
@CrossOrigin(origins = "http://localhost:8089")
public class NotificationUserController {
	
	@Autowired
	INotificationUserService notificationUserService;
	
	// http://localhost:8089/WomenEmpowerment/notificationuser/addNotificationuser/
	@PostMapping("/addNotificationuser/{description}/{email}")
	@ResponseBody
	public void sendPrivateNotification(@PathVariable("description") String description,@PathVariable("email") String email){
		notificationUserService.sendPrivateNotification(description, email);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/notificationuser/addGlobalnotificationuser/
	@PostMapping("/addGlobalnotificationuser/{description}")
	@ResponseBody
	public void sendGlobalNotification(@PathVariable("description") String description){
		notificationUserService.sendGlobalNotification(description);
	}
	
	// http://localhost:8089/WomenEmpowerment/notificationuser/readnotificationuser/
	@PutMapping("/readnotificationuser/{id}")
	@ResponseBody
	public void readNotificationUser(@PathVariable("id") Long id){
		notificationUserService.readNotificationUser(id);
	}
	
	// http://localhost:8089/WomenEmpowerment/notificationuser/deleteNotificationUser/
	@DeleteMapping("/deleteNotificationUser/{id}")
	@ResponseBody
	public void deleteNotificationUser(@PathVariable("id") Long id){
		notificationUserService.deleteNotificationUser(id);
	}

}
