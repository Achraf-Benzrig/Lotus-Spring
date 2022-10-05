package tn.esprit.spring.controller;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.Api;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.User.UserService;
import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.entity.Reservation;
import tn.esprit.spring.service.FileUploadService;
import tn.esprit.spring.service.IEvenementService;

@RestController
@Api(tags = "Event management")
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:8089")
public class evenementController {

	@Autowired
	IEvenementService EventService;

	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	UserService UserService;
	@Autowired
	private UserRepository myRepository;
	
	// http://localhost:8089/addevent/dd/df/
	@PostMapping("/addevent")
	@ResponseBody
	public Evenement addEvent(@RequestBody Evenement event) {
		return EventService.addEvent(event);
	}

	// http://localhost:8089/addeventon//
	@PostMapping("/addeventonly")
	@ResponseBody
	public Evenement addEventonly(@RequestParam("dateEvenement") String dateEvenement,
			@RequestParam("titre") String titre, @RequestParam("lieux") String lieux,
			@RequestParam("description") String description, @RequestParam("affiche") MultipartFile affiche)
			throws IllegalStateException, IOException, ParseException {
		Evenement event = new Evenement();
		Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateEvenement);
		event.setDateEvenement(sDate);
		event.setTitre(titre);
		event.setLieux(lieux);
		event.setDescription(description);
		fileUploadService.uploadfile(affiche);
		event.setAffiche(affiche.getOriginalFilename());
		return EventService.addEventonly(event);

	}

	// http://localhost:8089/addeventon//
	@PostMapping("/effectuerevent/{idevent}/{idres}/{idcag}")
	@ResponseBody
	public Evenement effectuerevent(@PathVariable("idevent") Long idevent, @PathVariable("idres") Long idres,
			@PathVariable("idcag") Long idcag) {
		return EventService.effectuer(idevent, idres, idcag);

	}
	
	@PostMapping("/getByLieux")
	@ResponseBody
	public List<Evenement> searchByLieux (@RequestParam("lieu") String lieu)
	{
		System.out.println(lieu);
		return EventService.getEventByAdress(lieu);
	}
	
	@GetMapping("/Recommend/{email}")
	public List<Evenement> recommend(@PathVariable("email") String email)
	{
		User currentUser = myRepository.findByEmail(email).orElse(null);
		String lieu = currentUser.getAdress();
		List<Evenement> listFinal = EventService.getEventByAdress(lieu);
		List<Evenement> therest = EventService.getallevent();
		listFinal.addAll(therest);
		
		return  listFinal;
	}
	
	@PostMapping("/addparticipant/{idEvent}/{email}")
	@ResponseBody
	public void addParticipant(@PathVariable("idEvent") Long idEvent, @PathVariable("email") String email)
	{
		EventService.addParticipant(idEvent, email);
	}
	
	@DeleteMapping("/deleteEvent/{idEvent}")
	@ResponseBody
	public void deleteEvent(@PathVariable("idEvent") Long idEvent)
	{
		EventService.deleteEvent(idEvent);
	}
}