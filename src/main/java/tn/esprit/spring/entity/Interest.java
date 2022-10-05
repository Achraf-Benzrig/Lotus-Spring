package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Interest implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	String nom;
	String interest;
	
	@ManyToMany(mappedBy="interests")
	private List<User> users;

}
