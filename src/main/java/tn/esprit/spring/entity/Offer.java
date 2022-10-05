package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.User.User;
import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Offer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	//@Size(min=5, max=20)
	private String Title;
	private String Domain;
	private String Place;
	private String description;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<User> user;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="offers")
	private Set<Entretien> entretiens;

	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="offer")
    private Set<Candidature> candidatures;
}
