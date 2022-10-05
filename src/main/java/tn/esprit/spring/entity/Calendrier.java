package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

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
public class Calendrier implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@FutureOrPresent(message="******** Oops ther is a validation error ******** ")
	private Date debut;
	@Temporal(TemporalType.TIMESTAMP)
	@Future(message="******** Oops ther is a validation error ******** ")
	@NotNull
	private Date fin;
	@JsonIgnore
	@ManyToOne
	private User user;
}
//dali