package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Cagnotte implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private float valeur;
	private Date dateDebut;
	private Date dateFin;
	
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cagnotte")
    private Set<Don> dons;
	
	@JsonIgnore
    @OneToOne
    private Evenement evenement;
}
