package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entity.ReportingReason;
import tn.esprit.spring.repository.ReportingRepository;
import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.Reporting;
import tn.esprit.spring.service.IReportingService;

@RestController
@Api(tags = "Reporting management")
@RequestMapping("/reporting")
@CrossOrigin(origins = "http://localhost:8089")
public class ReportingController {
	@Autowired
	IReportingService reportingService;
	
	
	// http://localhost:8089/WomenEmpowerment/reporting/addReport/
	@PostMapping("/addReport/{reported}/{reportedby}/{reason}")
	@ResponseBody
	public void addReport(@PathVariable("reported") String reported,@PathVariable("reportedby") String reportedby,@PathVariable("reason") String reason){
		reportingService.addReport(reported, reportedby, reason);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/reporting/deleteReport/
	@DeleteMapping("/deleteReport/{id}")
	@ResponseBody
	public void deleteReport(@PathVariable("id") Long id){
		reportingService.deleteReport(id);
	}
	
	// http://localhost:8089/WomenEmpowerment/reporting/getReports
	@GetMapping("/getReports")
	@ResponseBody
	public List<Reporting> getReports(){
		return reportingService.getReports();
	}
	
	// http://localhost:8089/WomenEmpowerment/reporting/findByUserFirstNameContains
	@GetMapping("/findByUserFirstNameContains/{firstName}")
	@ResponseBody
	public List<Reporting> findByUserFirstNameContains(@PathVariable("firstName") String firstName){
		return reportingService.findByUserFirstNameContains(firstName);
	}
	
	// http://localhost:8089/WomenEmpowerment/reporting/findByReasonContains
	@GetMapping("/findByReasonContains/{reason}")
	@ResponseBody
	public List<Reporting> findByReasonContains(@PathVariable("reason") String reason){
		return reportingService.findByReasonContains(reason);
	}
	
	// http://localhost:8089/WomenEmpowerment/reporting/countTotalReportingByReason
	@GetMapping("/countTotalReportingByReason")
	@ResponseBody
	public List<Object[]> countTotalReportingByReason(){
		return reportingService.countTotalReportingByReason();
	}
	

}


