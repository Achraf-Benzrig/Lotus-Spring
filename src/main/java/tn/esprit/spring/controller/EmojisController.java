package tn.esprit.spring.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/emojis")
public class EmojisController {
	@Autowired
	IEmojisService emoserv;




		@PostMapping("/add/{idCo}/{email}")
		@ResponseBody
		public void ajoutercategorie(@ RequestBody  Emojis emoj,@PathVariable("idCo")  Long idCo,@PathVariable("email") String email){
	emoserv.AjouterEmo(emoj, idCo, email);
		}
		
		
		
		@GetMapping("/")
		@ResponseBody
		public ResponseEntity<List<Emojis>> getUsers(){
			return ResponseEntity.ok().body(emoserv.getEmojis());
			
		}
		
		// http://localhost:8089/WomenEmpowerment/publication/delete/
		@DeleteMapping("/delete/{id}")
		@ResponseBody
		public void deleteUser(@PathVariable("id") Long id){
			emoserv.deleteEmojis(id);
			
		}
			

	}
