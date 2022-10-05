package tn.esprit.spring.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.FacebookData;
import tn.esprit.spring.repository.FacebookRepository;
@Slf4j
@Service
public class FacebookService implements IFacebookService{
	
	private String accessToken;
	
	@Autowired
	FacebookRepository facebookRepository;
	
	@Autowired
	UserRepository userRepository;

	@Value("${spring.social.facebook.app-id}")
	private String facebookAppId;
	
	@Value("${spring.social.facebook.app-secret}")
	private String facebookSecret;
	
	
	private FacebookConnectionFactory createConnection(){
		return new FacebookConnectionFactory(facebookAppId,facebookSecret);
	}
	
	@Override
	public String generateFacebookAuthorizeUrl() {
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://localhost:8089/WomenEmpowerment/face/facebook");
		params.setScope("email");
		return createConnection().getOAuthOperations().buildAuthenticateUrl(params);
	}

	@Override
	public void generateFacebookAccessToken(String code) {
		accessToken = createConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:8089/WomenEmpowerment/face/facebook", null).getAccessToken();
		
	}

	@Override
	public String getUserData() {
		Facebook facebook = new FacebookTemplate(accessToken);
		String[] fields = {"id","first_name","name"};
		return facebook.fetchObject("me", String.class,fields);
	}
	private Facebook facebook;

	@Override
	public PagedList<Post> getUserFeed(String email) {
		List<Post> posts = new FacebookTemplate(accessToken).feedOperations().getPosts();

		String regex = "^[a-zA-Z0-9]";
		 
		Pattern pattern = Pattern.compile(regex);
		for (Post post : posts) {
			FacebookData f = new FacebookData();
			
		
				
			
					 f.setMessage(post.getMessage());
				 
			
			User u = userRepository.findByEmail(email).orElse(null);
			f.setUser(u);
			f.setPostedAt(post.getCreatedTime());
			facebookRepository.save(f);
		}
		return new FacebookTemplate(accessToken).feedOperations().getFeed();
	}
			

}
