package tn.esprit.spring.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //(strategy = GenerationType.AUTO)
	private long id;
	
	//@Size(min=2, max=20)
	//@NotNull //(message="Question body not provided")
	private String text;
	
	//@ManyToOne
	//@JsonIgnore
	//private Quiz quiz;

	private String answers;

	//@ManyToMany(cascade = CascadeType.ALL)
	//private Set<Answer> answers;
}
