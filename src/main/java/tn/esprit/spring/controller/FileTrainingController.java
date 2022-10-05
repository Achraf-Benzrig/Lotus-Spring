package tn.esprit.spring.controller;


import org.springframework.stereotype.Controller;



import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller

public class FileTrainingController {
	

	
	 @PostMapping
	  public void uploadFile(@RequestParam("file") MultipartFile file) {
	  
	  }
	

}
