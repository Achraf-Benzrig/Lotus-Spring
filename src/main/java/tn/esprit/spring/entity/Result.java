package tn.esprit.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonProperty;
@EntityScan
public class Result {

	  @JsonProperty("items")
	  private List<Item> items = new ArrayList<>();
	    
	


	public class Item {
	    @JsonProperty("kind")
	    private String kind;
	    
	    @JsonProperty("etag")
	    private String etag;

	    @JsonProperty("snippet")
	    private List<Snippet> snippets = new ArrayList<>();
	     
	}


	public class Snippet {

	  @JsonProperty("channelId")
	  private String channelId;

	  @JsonProperty("title")
	  private String title;
	    
	}}