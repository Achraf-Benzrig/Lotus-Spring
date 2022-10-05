package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.User.User;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entretien implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idEntretien;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date dateEntretien;
	private String description;
	
	@JsonIgnore
	@ManyToOne
	Offer offers;
	
	
}
