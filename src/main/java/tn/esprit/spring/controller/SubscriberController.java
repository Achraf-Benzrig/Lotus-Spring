package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.Subscriber;
import tn.esprit.spring.service.ISubscriberService;

@RestController
@Api(tags = "Subscriber management")
@RequestMapping("/subscriber")
@CrossOrigin(origins = "http://localhost:8089")
public class SubscriberController {
	
	@Autowired
	ISubscriberService subscriberService;
	
	// http://localhost:8089/WomenEmpowerment/subscriber/addSubscriber/
	@PostMapping("/addSubscriber/{email}")
	@ResponseBody
	public void addSubscriber(@PathVariable("email") String email){
		subscriberService.addSubscriber(email);
	}
	
	// http://localhost:8089/WomenEmpowerment/subscriber/unSubscribe/
	@DeleteMapping("/unSubscribe/{email}")
	@ResponseBody
	public void unSubscribe(@PathVariable("email") String email){
		subscriberService.unSubscribe(email);
	}
	
	// http://localhost:8089/WomenEmpowerment/subscriber/extensionSubscribe/
	@PutMapping("/extensionSubscribe/{email}")
	@ResponseBody
	public void extensionSubscribe(@PathVariable("email") String email){
		subscriberService.extensionSubscribe(email);
	}
	
	// http://localhost:8089/WomenEmpowerment/subscriber/getSubscribers
	@GetMapping("/getSubscribers")
	@ResponseBody
	public List<Subscriber> getSubscribers(){
		return subscriberService.getSubscribers();
	}
	
	// http://localhost:8089/WomenEmpowerment/subscriber/countSubscriberByMonth
	@GetMapping("/countSubscriberByMonth")
	@ResponseBody
	public List<Object[]> countSubscriberByMonth(){
		return subscriberService.countSubscriberByMonth();
	}
	
	

}
