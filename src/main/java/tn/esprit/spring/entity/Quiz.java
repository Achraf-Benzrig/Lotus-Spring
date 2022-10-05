package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
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
public class Quiz implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idQuiz;
	
	//@Size(max = 100)
	@NotNull
	private String contenu;
	
    @Enumerated(EnumType.STRING)
    private TypeQuiz Type;
    
    private String name;
    
    @OneToOne
    private Training trainings;
    
    //@OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonIgnore
    //private List<Question> questions;
}
