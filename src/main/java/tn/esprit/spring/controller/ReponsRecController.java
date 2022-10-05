package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.ReponseRec;
import tn.esprit.spring.service.IReponseRecService;

@RestController
@RequestMapping("/ReponseRec")
public class ReponsRecController {

	@Autowired
	IReponseRecService rs;
	
	
	@PostMapping("/add/{recId}")
	@ResponseBody
	public String addReponse(@RequestBody ReponseRec reponse, @PathVariable("recId") Long id)
	{
		return rs.addReponse(reponse, id);
	}
	@GetMapping("/getTempsAttenteReclamation/{idReclamation}")
	@ResponseBody
	public long getTempsAttenteReclamation(@PathVariable("idReclamation") Long idRec)
	{
		return rs.getTempsAttenteReclamation(idRec);
	}
	
	@GetMapping("/getTempsAttenteMoyenReclamation")
	@ResponseBody
	public long getTempsAttenteMoyenReclamation()
	{
		return rs.getTempsAttenteMoyenReclamation();
	}
	@GetMapping("/suggestions/{idRec}")
	public List<String> suggest(@PathVariable("idRec") Long id){
		return rs.suggestion(id);
	}
}
