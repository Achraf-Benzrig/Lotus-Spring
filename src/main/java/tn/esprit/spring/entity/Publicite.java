package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.User.User;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Publicite implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@Enumerated(EnumType.STRING)
	private CanauxPub canaux;
	private Date dateDebut;
	private Date dateFin;
	private Long nbrVuesCible;
	private Long nbrVuesFinal;
	@Enumerated(EnumType.STRING)
	private PubType type;
	@OneToOne(mappedBy = "publicite")
	private PopulationCible populationCible;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
}
