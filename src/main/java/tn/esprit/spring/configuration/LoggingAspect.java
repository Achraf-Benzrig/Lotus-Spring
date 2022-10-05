package tn.esprit.spring.configuration;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.ToDoList;
import lombok.AllArgsConstructor;

import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.registration.token.ConfirmationTokenRepository;
import tn.esprit.spring.registration.token.ConfirmationTokenService;
import tn.esprit.spring.repository.PublicationRepository;
import tn.esprit.spring.service.IPublicationService;

import java.util.ArrayList;

import tn.esprit.spring.User.IUserManagement;
import tn.esprit.spring.registration.token.ConfirmationTokenRepository;
import tn.esprit.spring.registration.token.ConfirmationTokenService;
import tn.esprit.spring.service.INotificationUserService;

import java.time.LocalDateTime;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@AllArgsConstructor
@Component
@EnableAsync
@Aspect
public class LoggingAspect {
	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

	private final ConfirmationTokenService confirmationTokenRepository;

	private final PublicationRepository pubrepo;

	
	
	private final UserRepository userRepository;
	
	@Autowired
	IUserManagement userService;
	
	@Autowired
	INotificationUserService notificationUserService;

	@Async
	@Scheduled(cron="0 0 0 * * ?")
	public void toDoNotif() {
		List<User> u = userService.getUsers();
		for (User user : u) {
			List<ToDoList> todo = (List<ToDoList>) user.getTodolist();
			for (ToDoList toDoList : todo) {
				//if(toDoList.getDebutDate().compareTo(LocalDateTime.now().) == 0){
					
				//}
			}
		}
	}
	

	@Async
	@Scheduled(cron="0 0 0 * * ?")
	public void fixedRateMethod() {
		confirmationTokenRepository.deleteAllTokens();
	}

	@Autowired
	IPublicationService pubserv;
	
	@Async
	@Scheduled(fixedRate=30000000)
	public void deletePub() {
		List<Publication> p= pubserv.getPublications();
		for (Publication publication : p) {
			if(publication.getCommentairesPub().size()==0){
				
				pubserv.deletePub(publication.getId());
			}
			
		}
		
	
	}
	@Async
	@AfterReturning("execution(* tn.esprit.spring.service.PublicationService.AjouterPub(..))")
	public void deletePub1() {
		List<Publication> p1= pubrepo.findAll();
		List<String> list1 = new ArrayList<String>(); 
	     list1.add("xxx"); 
	     list1.add("xx"); 
	     list1.add("x");
		List<Publication> p= pubserv.getPublications();
		for (Publication publication : p) {
			if(list1.contains(publication.getTitle())){
				System.out.println("mots Interdit title");
				
				pubserv.deletePub(publication.getId());
				
			}
			else if (list1.contains(publication.getDescription())){
				System.out.println("mots Interdit description");
				
				pubserv.deletePub(publication.getId());
				
			}
		}}
	

	
	@Async
	@Scheduled(fixedRate = 300000)
	public void UserReport() {
		List<User> u = userService.getUsers();
		for (User user : u) {
			//logger.info("Method with fixed Rate");
			if(user.getReporting().size()>=2){
				userRepository.lockedAppUser(user.getEmail());

				
			}
			
		}

	
	}
	
	
	
	}
	
	

