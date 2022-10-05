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
@RequestMapping("/likes")
public class LikeController {
@Autowired
ILikesService likser;




	@PostMapping("/add/{idCo}/{email}")
	@ResponseBody
	public void ajoutercategorie(@ RequestBody  Likes lik,@PathVariable("idCo")  Long idCo,@PathVariable("email") String email){
	likser.AjouterLik(lik, idCo, email);
	
	
	}

	@GetMapping("/getlik/{pub}")
	@ResponseBody
	public long getlik(@PathVariable("pub") Long pub){
		return likser.getlikpar(pub);
		
	}

	@GetMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long id){
		 likser.deleteLikes(id);;
		
	}
	



	@GetMapping("/")
	@ResponseBody
	public ResponseEntity<List<Likes>> getUsers(){
		return ResponseEntity.ok().body(likser.getLikes());
		
	}
	
	
	
	@GetMapping("/getlikedby/{publ}")
	@ResponseBody
	public List<String> getlikedby(@PathVariable("publ") Long publ){
		return likser.displayLiedby(publ);
		
	}


}
