package tn.esprit.spring.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entity.Offer;
import tn.esprit.spring.service.IOfferService;

@Api(tags = "Offer management")
@RestController
@RequestMapping("/Offer")
public class OfferController {
	
	@Autowired
	IOfferService offerService;
	
	@GetMapping("/statistic")
	@ApiOperation(value = "statistic ")
	public  List<Object[]> statistic  ()
	{
	return offerService.statistic(); }
	
	
	@PostMapping("/add-offer")
	@ResponseBody
	public void AddOffer(@RequestBody Offer f) {
		offerService.AddOffer(f);	
	}
	
	@GetMapping("/offers")
	@ResponseBody
	public List<Offer> getAllOffers(){
		return offerService.getAllOffers();
	}
	
	@DeleteMapping("/delete-offer/{IdOffer}")
	//@RequestMapping(value = "/delete-offer", method = RequestMethod.DELETE)
	@ResponseBody
	public void DeleteOffer(@PathVariable Long IdOffer ) {
		offerService.DeleteOffer(IdOffer );
	}
	
	@PutMapping("/update-offer")
	@ResponseBody
	public void UpdateOffer(@RequestBody Offer f) 
	{
		offerService.UpdateOffer(f);	
	}
	//Filter
	
	@GetMapping("/search/{keyword}")
	public List<Offer> search(@PathVariable("keyword") String keyword) {
       return  this.offerService.search (keyword); 
    }
	//Recherche
	@GetMapping("/FindOfferByTitle/{Title}")
	@ResponseBody
	public List<Offer> FindOfferByTitle(@PathVariable String Title) 
	{
		return  this.offerService.FindOfferByTitle(Title);
	}
	//JobApplication
	@PostMapping("/apply/{IdOffer}/{email}") //*******************************************************************
	@ResponseBody
	public void JobApplication(@PathVariable("IdOffer") Long IdOffer,@PathVariable("email") String email){
		offerService.JobApplication(IdOffer, email);
	}
	
	@GetMapping("/suggestedOffer/{idUser}")
	@ResponseBody
	public List<Offer> suggestedOffer(@PathVariable("idUser")Long idUser){
		return offerService.suggestedOffer(idUser);
		
	}
	
	@PostMapping("/AffecterOfferByUserId/{IdOffer}/{email}")//***************************************************
	@ResponseBody
	public void AffecterOfferByUserId(@PathVariable("IdOffer") Long IdOffer,@PathVariable("email") String email){
		offerService.AffecterOfferByUserId(IdOffer,email);
		
	}

	//Recherche not sure one or two
	
	//@RequestMapping(value="/offers/{IdOffer}")
	//public Offer FindOfferById(@PathVariable Offer f){  //Not Sure !!!!!!!!!!!!!!!
		
		//return offerService.FindOfferById(f);
	//}
	
	/*  private String saveDirectory = "E:/Test/Upload/";
	     
	    @RequestMapping(method = RequestMethod.POST)
	    public String handleFileUpload(HttpServletRequest request,
	            @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
	         
	        System.out.println("description: " + request.getParameter("description"));
	         
	        if (fileUpload != null && fileUpload.length > 0) {
	            for (CommonsMultipartFile aFile : fileUpload){
	                 
	                System.out.println("Saving file: " + aFile.getOriginalFilename());
	                 
	                if (!aFile.getOriginalFilename().equals("")) {
	                    aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
	                }
	            }
	        }
	 
	        // returns to the view "Result"
	        return "Result";
	    }*/
}
