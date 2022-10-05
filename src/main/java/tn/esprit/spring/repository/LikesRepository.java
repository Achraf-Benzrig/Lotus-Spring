package tn.esprit.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.Likes;
import tn.esprit.spring.entity.Offer;

import javax.persistence.Entity;

@Repository
@Transactional(readOnly = true)
public interface LikesRepository extends JpaRepository<Likes, Long>{

  Optional<Likes> findByLikedBy(String liked_By);
//	@Query("SELECT COUNT(liked_by) FROM Likes l WHERE l.commentairesPubs= :pub")
//	int  egwfwwef( @Param("pub") Long pub);
	
	
	@Query(value="select count(liked_by) from Likes l where l.commentaires_pubs_id=:pub", nativeQuery = true)
	long countBylikes(@Param("pub")long pub);
	
	@Query(value="select liked_by from Likes l where l.commentaires_pubs_id=:publ", nativeQuery = true)
	List<String> displaylikedby(@Param("publ")long publ);
	
	

	

	
	
	
    Likes findByCommentairesPubs(Long id);
    
    
    
    
}
