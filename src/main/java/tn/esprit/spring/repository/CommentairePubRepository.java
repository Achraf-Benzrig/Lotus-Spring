package tn.esprit.spring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.User.UserRole;
import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Reporting;
import tn.esprit.spring.entity.TypePub;

@Repository
public interface CommentairePubRepository extends JpaRepository<CommentairePub, Long>{

	@Query("SELECT c FROM CommentairePub c join c.publications p  where p.title= :title or p.description=:description or p.type=:type or c.commented_By=:users  ")
    public List<CommentairePub> suggestedCom( @Param("title") String title,@Param("description") String description,@Param("type") TypePub type ,@Param("users") String users );
	

	@Query("SELECT COUNT(liked_by) FROM Likes l WHERE l.commentairesPubs= :pub")
	int  egwfwwef( @Param("pub") Long pub);
	

	@Query("SELECT c FROM CommentairePub c join c.publications p WHERE p.id= :pub")
	public List <CommentairePub>  Lisaaa( @Param("pub") Long pub);
	
	
}
