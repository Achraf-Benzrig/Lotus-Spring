package tn.esprit.spring.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Evenement implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateEvenement;
	private String titre;
	private String lieux;
	private String affiche;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TypeEvenement typeEvenement;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<User> participants;
	
	
    @OneToOne
    private Cagnotte cagnotte;
	
	
    @OneToOne
    private Reservation reservation;
}
