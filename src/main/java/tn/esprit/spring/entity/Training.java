package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.spring.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Training implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idFormation;
	
	private String title;
	private String description;
	private Date dateDebut;
	private Date dateFin;
	private Long nbrMaxApprenant;
	private String affiche;
	private String formateur;
	
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<User> users;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="trainings")
	private Set<CommentaireTraining> commentairesTraining;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="trainings")
	private Set<Ressource> ressources;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="trainings")
	private Set<Certificate> certificates;
	
	@OneToOne(mappedBy="trainings")
	private Quiz quiz;
	
	@JsonIgnore
	@ManyToOne
	Interest intereset;
}
