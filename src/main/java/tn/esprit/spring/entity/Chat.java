package tn.esprit.spring.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import tn.esprit.spring.User.User;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chat implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String message;
	private String response;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<User> users;
}
