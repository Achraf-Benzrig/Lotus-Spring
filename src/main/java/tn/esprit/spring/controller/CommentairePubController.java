package tn.esprit.spring.controller;

import java.time.LocalDateTime;
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

import tn.esprit.spring.User.UserRole;
import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.TypePub;
import tn.esprit.spring.service.ICommentairePubService;
import tn.esprit.spring.service.IPublicationService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/commentaire")
public class CommentairePubController {
	@Autowired
	ICommentairePubService comserv;
	@CrossOrigin(origins = "*")
	@PostMapping("/add/{idPub}/{email}")
	@ResponseBody
	public void ajoutercategorie(@ RequestBody  CommentairePub com,@PathVariable("idPub") Long idPub,@PathVariable("email") String email){
		comserv.AjouterCommentare(com, idPub, email);
	}

	// http://localhost:8089/WomenEmpowerment/commentaire
	@GetMapping("/")
	@ResponseBody
	public ResponseEntity<List<CommentairePub>> getUsers(){
		return ResponseEntity.ok().body(comserv.getCommentairePub());
		
	}
	@GetMapping("/get/{pub}")
	@ResponseBody
	public List<CommentairePub> retrieveTraining(@PathVariable("pub") Long pub ) {
	return comserv.Lisaa(pub);}
	
	
	// http://localhost:8089/WomenEmpowerment/publication/delete/
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void deleteUser(@PathVariable("id") Long id){
		comserv.deleteCom(id);
		
	}

	
	//http://localhost:8089/WomenEmpowerment/publication/update
	@PutMapping("/update")
	@ResponseBody
	public void updateUser(@RequestBody CommentairePub comP) {
		comserv.updateCom(comP);
	
	}	
	
	// http://localhost:8089/WomenEmpowerment/commentaire
	@GetMapping("/getlik/{id}")
	@ResponseBody
	public int getlikes(@PathVariable("id") Long id)
{
		return comserv.nbreLikeParCmnt(id);
		
	}

	
	@GetMapping("/sug/{title}/{description}/{type}/{users}")
	public List<CommentairePub> search(@PathVariable("title") String title,@PathVariable("description") String description,@PathVariable("type") TypePub type,@PathVariable("users") String users) {
       return  comserv.dasd(title, description, type,users);
   }
	
}

