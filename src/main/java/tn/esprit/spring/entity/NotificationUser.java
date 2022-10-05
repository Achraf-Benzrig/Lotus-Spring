package tn.esprit.spring.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class NotificationUser {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Boolean old = false;
	private LocalDateTime createdAt;
	private LocalDateTime readAt;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	User user;

}
