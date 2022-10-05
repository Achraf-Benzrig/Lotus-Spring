package tn.esprit.spring.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;


import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.logging.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.service.*;
import tn.esprit.spring.Response.Com;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRole;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import tn.esprit.spring.entity.*;;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/publication")
public class PublicationController {


	@Autowired
	
	IPublicationService pubserv;
	@Autowired
	ServletContext context;

	@RequestMapping(value= "/add/{email}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, "text/csv" })
	

	
	@ResponseBody()
	public Com addPost(@RequestParam("file") MultipartFile file,@RequestParam("body") String publication,@PathVariable("email") String email ) throws JsonParseException , JsonMappingException , Exception {
		Publication LL = new ObjectMapper().readValue(publication, Publication.class);

		boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
        	new File (context.getRealPath("/Images/")).mkdir();
        	System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        System.out.println(context.getContextPath());
        try
        {
        	System.out.println("Image");
        	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
        	 
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
		
        LL.setPicture(newFileName);
        LL.setTitle(LL.getTitle());
        LL.setDescription(LL.getDescription());
	
		return new Com(pubserv.AjouterPub(LL, email));
	}
	@GetMapping("/Imgarticles/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long idPost) throws Exception{
		 
		 Publication p   =pubserv.retrievePostById(idPost);
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+p.getPicture()));
	 }
		
		// http://localhost:8089/WomenEmpowerment/publication
		@GetMapping("/")
		@ResponseBody
		public ResponseEntity<List<Publication>> getUsers(){
			return ResponseEntity.ok().body(pubserv.getPublications());
			
		}
		@GetMapping("/get/{id}")
		@ResponseBody
		public Publication retrieveTraining(@PathVariable("id") Long id) {
		return pubserv.getPubID(id);}
		
		// http://localhost:8089/WomenEmpowerment/publication/delete/
		@DeleteMapping("/delete/{id}")
		@ResponseBody
		public void deleteUser(@PathVariable("id") Long id){
			pubserv.deletePub(id);
			
		}
		
		
		//http://localhost:8089/WomenEmpowerment/publication/update
		@PutMapping("/update/{email}")
		@ResponseBody
		public void updateUser(@RequestBody Publication pub,@PathVariable("email")String email) {
			pubserv.updatePub(pub, email);
		
		}	
		
		@GetMapping("/search/{keyword}")
		public List<Publication> search(@PathVariable("keyword") String keyword) {
	       return  this.pubserv.search (keyword); 
	    }
		
		
		@GetMapping("/sug/{description}/{companyname}/{assoc}/{role}/{age}")
		public List<Publication> search(@PathVariable("description") String description,@PathVariable("companyname") String companyname,@PathVariable("assoc") String assoc,@PathVariable("role") UserRole role,@PathVariable("age") int age) {
	       return  this.pubserv.suggpub(description, companyname, assoc, role, age);
	    }
		
		
		
		
		
		
		
		
		
	}