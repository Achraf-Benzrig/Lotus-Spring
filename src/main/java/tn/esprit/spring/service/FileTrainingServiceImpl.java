package tn.esprit.spring.service;

import java.io.File;
import java.io.IOException;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FileTrainingServiceImpl {
	
	public void uploadfile(MultipartFile file) throws IllegalStateException, IOException{
		file.transferTo(new File("C:\\Users\\Loujein\\Documents\\GitHub\\PIProjectBackend\\src\\main\\files\\"+file.getOriginalFilename()));
	}
}
