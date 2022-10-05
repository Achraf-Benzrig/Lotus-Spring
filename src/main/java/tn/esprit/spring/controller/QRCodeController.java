package tn.esprit.spring.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Subscriber management")
@CrossOrigin(origins = "http://localhost:8089")
public class QRCodeController {
	
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";


	@Autowired
	
	IQRCodeGenerator qRCodeGenerator;
	
	
    @GetMapping(value = "/genrateAndDownloadQRCode/{codeText}")
    @ResponseBody
		public void download(@PathVariable("codeText") String codeText) throws WriterException, IOException
		{
    		qRCodeGenerator.generateQRCodeImage(codeText, QR_CODE_IMAGE_PATH);
		}


}
