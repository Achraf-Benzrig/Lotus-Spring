package tn.esprit.spring.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.entity.Blacklist;
import tn.esprit.spring.entity.Calendrier;
import tn.esprit.spring.entity.Candidature;
import tn.esprit.spring.entity.Chat;

import tn.esprit.spring.entity.Interest;

import tn.esprit.spring.entity.FacebookData;
import tn.esprit.spring.entity.NotificationUser;
import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Calendrier;
import tn.esprit.spring.entity.Chat;
import tn.esprit.spring.entity.Don;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.entity.Offer;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Publicite;
import tn.esprit.spring.entity.Reclamation;
import tn.esprit.spring.entity.RendezVous;
import tn.esprit.spring.entity.ReponseRec;
import tn.esprit.spring.entity.Reporting;
import tn.esprit.spring.entity.Subscriber;
import tn.esprit.spring.entity.SuspiciousAccount;
import tn.esprit.spring.entity.ToDoList;
import tn.esprit.spring.entity.Training;
import tn.esprit.spring.entity.UserEmotions;
import tn.esprit.spring.registration.token.ConfirmationToken;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails,Serializable {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;
    private String picture ;
    private String adress ;
    private String companyName ;
    private String numTel ;
    private String associationName ;
    private String description ;
    private String CIN ;
    private String availability ;
    private int age ;
    private String profession;
    private String niveauetude ;



    private LocalDateTime birthdate;
    

    @ManyToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Offer> offers;
    
    @JsonIgnore
    @ManyToMany(mappedBy="users", cascade = CascadeType.ALL)
    private Set<Chat> chat;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="users")
    private Set<Publication> publications;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="user")
    private Set<Reporting> reporting;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="user")
    private Set<NotificationUser> notificationUser;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="user")
    private Set<FacebookData> facebookData;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="user")
    private Set<ToDoList> todolist;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<ReponseRec> ReponsRec;
    
    @JsonIgnore
    @ManyToMany(mappedBy="users", cascade = CascadeType.ALL)
    private Set<Training> trainings;
    
    @ManyToMany()
    private List<Interest> interests;
    
    
    @JsonIgnore
    @OneToOne
    private Subscriber subscriber;
    
    @JsonIgnore
    @OneToOne
    private UserEmotions userEmotions;
    
    @JsonIgnore
    @OneToOne(mappedBy="user")
    private SuspiciousAccount suspiciousAccounts;
    
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<RendezVous> rendezVous;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Reclamation> reclamations;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<ConfirmationToken> confirmationTokens;
    
    
    @JsonIgnore
    @ManyToMany( cascade = CascadeType.ALL, mappedBy="user")
    private Set<Publicite> publicites;
    
    @JsonIgnore
    @ManyToMany( cascade = CascadeType.ALL, mappedBy="user")
    private Set<Calendrier> calendriers;
    

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Candidature> candidatures;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Don> dons;
    
    @JsonIgnore
    @ManyToMany(mappedBy="participants", cascade = CascadeType.ALL)
    private Set<Evenement> evenements;
    

    
	public User(Long id, String firstName, String lastName, String email, String password, UserRole appUserRole,
			String picture, String adress, String companyName, String numTel,
			String associationName, String description, String cIN, String availability, int age,
			Subscriber subscriber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
		this.picture = picture;
		this.adress = adress;
		this.companyName = companyName;
		this.numTel = numTel;
		this.associationName = associationName;
		this.description = description;
		CIN = cIN;
		this.availability = availability;
		this.age = age;
		this.subscriber = subscriber;
	}
	
	public User(String firstName, String lastName, String email, String password, UserRole appUserRole,
			String picture, String adress, String companyName, String numTel,
			String associationName, String description, String cIN, String availability, int age,
			Subscriber subscriber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
		this.picture = picture;
		this.adress = adress;
		this.companyName = companyName;
		this.numTel = numTel;
		this.associationName = associationName;
		this.description = description;
		CIN = cIN;
		this.availability = availability;
		this.age = age;
		this.subscriber = subscriber;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
