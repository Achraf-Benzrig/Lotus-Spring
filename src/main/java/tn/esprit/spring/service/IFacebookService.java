package tn.esprit.spring.service;

import java.util.List;

import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.entity.FacebookData;

public interface IFacebookService {
	public String generateFacebookAuthorizeUrl();
	public void generateFacebookAccessToken(String code);
	public String getUserData();
	public PagedList<Post> getUserFeed(String email);

}
