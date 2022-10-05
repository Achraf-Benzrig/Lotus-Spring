package tn.esprit.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.esprit.spring.entity.Training;
import tn.esprit.spring.service.TrainingPdf;
import tn.esprit.spring.service.TrainingService;
@RestController
@RequestMapping("/pdf")
public class TrainingPdfController {
	
	@Autowired
	TrainingService service;
	
	@GetMapping("/training/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Training> listTraining = service.retrieveAllTrainings();

        TrainingPdf exporter = new TrainingPdf(listTraining);
        exporter.export(response);
	}

}
