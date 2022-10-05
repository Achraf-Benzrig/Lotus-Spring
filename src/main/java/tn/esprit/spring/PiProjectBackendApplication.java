package tn.esprit.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.spring.config.twilioConfig;

import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import tn.esprit.spring.entity.Result;


import nu.pattern.OpenCV;
@EnableScheduling
@Configuration
@SpringBootApplication
@RestController
public class PiProjectBackendApplication {
	
	
	public static void main(String[] args) {
		OpenCV.loadShared();
		SpringApplication.run(PiProjectBackendApplication.class, args);
	}
	@GetMapping("/youtube-data")
	public Object youtube(
			@RequestParam String key, 
			@RequestParam String part, 
			@RequestParam String q, 
			
			@RequestParam String type,
			@RequestParam String maxResults) {
		//AIzaSyBdwvLG4HRgLGxSHJvBPGQD9vBt2i82XYg
		String url = "https://www.googleapis.com/youtube/v3/search?key={key}&part={part}&q={q}&type={type}&maxResults={maxResults}";
		
		Map<String,String> params = new HashMap<>();		
		params.put("key", key);
		params.put("part", part);
		params.put("q", q);
	
		params.put("type", type);
		params.put("maxResults", maxResults);
		
		RestTemplate rest = new RestTemplate();
		
	
		return rest.getForObject(url, Object.class, params);
		
	}

}
