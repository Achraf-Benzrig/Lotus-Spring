package tn.esprit.spring.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserService;
import tn.esprit.spring.entity.HistoriqueTraining;
import tn.esprit.spring.entity.Training;
import tn.esprit.spring.repository.HistoriqueTrainingRepository;
import tn.esprit.spring.repository.TrainingRepository;
import tn.esprit.spring.service.FileTrainingServiceImpl;
import tn.esprit.spring.service.HistoriqueTrainingService;
import tn.esprit.spring.service.TrainingService;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/Training")
public class TrainingController {
	
	@Autowired
	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	TrainingService trainService;
	@Autowired
	UserService userService;
	@Autowired
	FileTrainingServiceImpl fileupload;
	@Autowired
	TrainingRepository trainRepo;
	
	@Autowired
	HistoriqueTrainingService historiqueTrainiSer;
	
	
	
	//http://localhost:8089/WomenEmpowerment/Training/add-training
			@PostMapping("/add-training/{email}")
			
			public void ajouterFormation(@RequestParam("title") String title,@RequestParam("description") String description,
					@RequestParam("dateDebut") String dateDebut,@RequestParam("dateFin") String dateFin,@RequestParam("nbrMaxApprenant") Long nbrMaxApprenant,
					@RequestParam("file") MultipartFile file,@PathVariable("email") String email) throws IllegalStateException, IOException, ParseException
			{
				
				Date dateDeb =new SimpleDateFormat("yyyy/MM/dd").parse(dateDebut);
				Date dateF =new SimpleDateFormat("yyyy/MM/dd").parse(dateFin);
				Training training = new Training();
				training.setTitle(title);
				training.setDescription(description);
				training.setDateDebut(dateDeb);
				training.setDateFin(dateF);
				training.setNbrMaxApprenant(nbrMaxApprenant);
				fileupload.uploadfile(file);
				training.setAffiche(file.getOriginalFilename());
				trainService.ajouterFormation(training,email);
				
				
			}
			
	       //http://localhost:8089/WomenEmpowerment/Training/	
			@PutMapping("/update-Training/{email}")
			@ResponseBody
			public Training updateTraining(@RequestParam("idFormation")Long idFormation,@RequestParam("title") String title,@RequestParam("description") String description,
					@RequestParam("dateDebut") String dateDebut,@RequestParam("dateFin") String dateFin,@RequestParam("nbrMaxApprenant") Long nbrMaxApprenant,
					@RequestParam("file") MultipartFile file,@PathVariable("email") String email) throws ParseException, IllegalStateException, IOException {
				Date dateDeb =new SimpleDateFormat("yyyy/MM/dd").parse(dateDebut);
				Date dateF =new SimpleDateFormat("yyyy/MM/dd").parse(dateFin);
				Training training = new Training();
				training.setIdFormation(idFormation);
				training.setTitle(title);
				training.setDescription(description);
				training.setDateDebut(dateDeb);
				training.setDateFin(dateF);
				training.setNbrMaxApprenant(nbrMaxApprenant);
				training.setFormateur(email);
				fileupload.uploadfile(file);
				training.setAffiche(file.getOriginalFilename());
			return trainService.updateTraining(training ,email);
			}
			
			//http://localhost:8089/WomenEmpowerment/Training/remove-training/{training-id}
			@DeleteMapping("/remove-training/{training-id}")
			@ResponseBody
			public void removeTraining(@PathVariable("training-id") Long trainingId) {
				trainService.deleteTraining(trainingId);
			}
			
			//http://localhost:8089/WomenEmpowerment/Training/retrieve-all-Training
			@GetMapping("/retrieve-all-Training")
			@ResponseBody
			public List<Training> retrieveAllTrainings (){
			List<Training> listtraining = trainService.retrieveAllTrainings();
			return listtraining;
			}
			
			//http://localhost:8089/WomenEmpowerment/Training/retrieve-Training/{training-id}
			@GetMapping("/retrieve-Training/{training-id}")
			@ResponseBody
			public Training retrieveTraining(@PathVariable("training-id") Long trainingId) {
			return trainService.retrieveTraining(trainingId);
			}
			
			@PostMapping("/affecterUserFormation/{idUser}/{idFormation}") 
			@ResponseBody 
			public void affecterUserFormation(@PathVariable("idUser")Long idUser,@PathVariable("idFormation") Long idFormation) {
				trainService.affecterUserFormation(idUser, idFormation);
				
			}
			
			//http://localhost:8089/WomenEmpowerment/Training
			//suggestion des evenements 
			@GetMapping("/suggestedEvents")
			public List<Training>suggestTraining(){
				Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				String uname;
				if(principal instanceof UserDetails)
				{
					uname=((UserDetails)principal).getUsername();
				}  else
				{
				uname=principal.toString();	
				}
				return trainService.suggestTraining(uname);
				
			}
			//http://localhost:8089/WomenEmpowerment/Training/search
			@GetMapping("/search/title")
		    public  ResponseEntity<List<Training>>getTrainingByTitle(@RequestParam String title) {
		      
		        return new ResponseEntity<List<Training>>(trainRepo.findBytitle(title),HttpStatus.OK);
		    }
			
			//http://localhost:8089/WomenEmpowerment/Training/search
			@GetMapping("/search/formateur")
		    public  ResponseEntity<List<Training>>getTrainingByformateur(@RequestParam String formateur) {
		      
		        return new ResponseEntity<List<Training>>(trainRepo.findByformateur(formateur),HttpStatus.OK);
		    }
			
			
			
			//search
			
			@PostMapping("/Search")
			public List<Training> searchTraining(@RequestParam String text)
			{
				User currentUser=new User();
				currentUser.setId((long)1);
				HistoriqueTraining hist= new HistoriqueTraining();
				hist.setUser(currentUser);
				hist.setSearch(text);
				historiqueTrainiSer.ajouterHistorique(hist);
				return trainService.searchTraining(text);
				
			}
			@GetMapping("/recomended")
			public List<Training> recomended()
			{
				User currentUser=new User();
				currentUser.setId((long)1);
				List<HistoriqueTraining>histList=historiqueTrainiSer.getHistoriqueByUser(currentUser);
				List<Training> recomended=new ArrayList<Training>();
				for (int i=0;i<histList.size();i++)
				{
					List<Training>trainings=trainService.searchTraining(histList.get(i).getSearch());
				   recomended.addAll(trainings);
				
				}
				Set<Training> listedrecom = new HashSet<Training>(recomended);
				List<Training> recom = new ArrayList<Training>(listedrecom);
				return recom;
				
			}
			
			
			

}



