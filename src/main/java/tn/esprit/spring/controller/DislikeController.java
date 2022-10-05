package tn.esprit.spring.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.*;
import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.*;;
@RestController
@CrossOrigin()
@RequestMapping("/dislike")
public class DislikeController {
	@Autowired
	IDislikeService dislikser;




		@PostMapping("/add/{idCo}/{email}")
		@ResponseBody
		public void ajoutercategorie(@ RequestBody  Dislike dislik,@PathVariable("idCo")  Long idCo,@PathVariable("email") String email){
		dislikser.AjouterDislik(dislik, idCo, email);
		}
			
		@DeleteMapping("/delete/{id}")
		@ResponseBody
		public void delete(@PathVariable("id") Long id){
			 dislikser.deleteDislikes(id);
			
		}
		



		@GetMapping("/")
		@ResponseBody
		public ResponseEntity<List<Dislike>> getUsers(){
			return ResponseEntity.	ok().body(dislikser.getDislikes());
			
		}
		
	}
