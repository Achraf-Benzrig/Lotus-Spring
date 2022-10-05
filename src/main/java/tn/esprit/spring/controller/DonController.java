package tn.esprit.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Don;
import tn.esprit.spring.service.IDonService;

@RestController
@Api(tags = "Don management")
@RequestMapping("/don")
@CrossOrigin(origins = "http://localhost:8089")


public class DonController {

	@Autowired
	IDonService donService;
	
	@PostMapping("/adddon")
	public Don addDon(@RequestParam("idcag") Long idcag,@RequestParam("montant") float montant,@RequestParam("datedon") String datedon,@RequestParam("email") String email  ) throws ParseException
	{
		Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(datedon);

		return	donService.addDon(idcag,montant,sDate,email);
		
	}
}
