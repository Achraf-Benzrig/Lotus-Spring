package tn.esprit.spring.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	public void uploadfile(MultipartFile file) throws IllegalStateException, IOException{
		file.transferTo(new File("C:\\PiDevFiles\\"+file.getOriginalFilename()));
	}
}
