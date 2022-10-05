package tn.esprit.spring.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.spring.User.User;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentairePub implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
//	@NotNull()
//	@NotBlanc

	private String comment ; 
	private String description;
	private int commenters;
	private String emojis;
	private Long likes;
	private Long dislike;
	private int likesss;
	
	private String commented_By;
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	@JsonIgnore
	@ManyToOne
	Publication publications;
	
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="commentairesPub")
    private Set<MotsInterdit> motsInterdits;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="commentairesPubs")
    private Set<Likes> likess;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="commentairesPubss")
    private Set<Dislike> dislikess;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="commentairesPubsss")
    private Set<Emojis> emojiss;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmojis() {
		return emojis;
	}

	public void setEmojis(String emojis) {
		this.emojis = emojis;
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public Long getDislike() {
		return dislike;
	}

	public void setDislike(Long dislike) {
		this.dislike = dislike;
	}

	public String getCommented_By() {
		return commented_By;
	}

	public void setCommented_By(String commented_By) {
		this.commented_By = commented_By;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Publication getPublications() {
		return publications;
	}

	public void setPublications(Publication publications) {
		this.publications = publications;
	}

	public Set<MotsInterdit> getMotsInterdits() {
		return motsInterdits;
	}

	public void setMotsInterdits(Set<MotsInterdit> motsInterdits) {
		this.motsInterdits = motsInterdits;
	}

	public Set<Likes> getLikess() {
		return likess;
	}

	public void setLikess(Set<Likes> likess) {
		this.likess = likess;
	}

	public Set<Dislike> getDislikess() {
		return dislikess;
	}

	public void setDislikess(Set<Dislike> dislikess) {
		this.dislikess = dislikess;
	}

	public Set<Emojis> getEmojiss() {
		return emojiss;
	}

	public void setEmojiss(Set<Emojis> emojiss) {
		this.emojiss = emojiss;
	}

	public int getLikesss() {
		return likesss;
	}

	public void setLikesss(int likesss) {
		this.likesss = likesss;
	}
    
    
}
