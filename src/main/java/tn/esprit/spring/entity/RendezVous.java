package tn.esprit.spring.entity;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
public class RendezVous implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull
	private RendezVousType rendezVousType;
	@FutureOrPresent(message="******** Oops ther is a validation error ******** ")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date debut;
	@FutureOrPresent(message="******** Oops ther is a validation error ******** ")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date fin;
	private String description;
	private String code  ;
	private boolean confirmed=false;
	@JsonIgnore
	@ManyToOne
	private User user;
}
