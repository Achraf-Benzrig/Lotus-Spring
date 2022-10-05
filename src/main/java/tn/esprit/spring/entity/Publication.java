package tn.esprit.spring.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.userdetails.UserDetails;

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
public class Publication implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	 @Enumerated(EnumType.STRING)
	private TypePub type;
	private String picture;

	
	private int commentrs;

	private LocalDateTime createdAt;

	@ManyToOne
	User users;
	
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="publications")
    private Set<Rating> ratings;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="publications")
    private Set<CommentairePub> commentairesPub;

	public Publication(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

}
