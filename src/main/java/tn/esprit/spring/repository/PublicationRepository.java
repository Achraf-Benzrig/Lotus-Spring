package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.User.UserRole;
import tn.esprit.spring.entity.Offer;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Reporting;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long>{
	@Query("SELECT p FROM Publication p WHERE p.title LIKE %?1%" 
            + " OR p.description LIKE %?1%"
            + " OR p.type LIKE %?1%"
            +"OR p.users Like %?1% ")
        
    public List<Publication> search(String keyword);
	
	
	
	//@Query("SELECT P FROM Publication P join P.commentairesPub c join P.users u join P.ratings r where u.description= :description or u.companyName=:companyname or u.associationName=:assoc or u.enabled= true or u.appUserRole=:role or u.age=:age or c.description=:descriptionCom or r.rate = (select max(b.rate) from Rating b )")
    //public List<Publication> suggestedPub( @Param("description") String description,@Param("companyname") String companyname,@Param("assoc") String assoc,@Param("role") UserRole role,@Param("age") int age ,@Param("descriptionCom") String descriptionCom );
	
	@Query("SELECT P FROM Publication P  join P.users u  where u.description= :description or u.companyName=:companyname or u.associationName=:assoc and u.enabled= true and u.appUserRole=:role and u.age=:age ")
    public List<Publication> suggestedPub( @Param("description") String description,@Param("companyname") String companyname,@Param("assoc") String assoc,@Param("role") UserRole role,@Param("age") int age );
	
	
	

	@Query("SELECT count(*) FROM Publication p where p.users =:id")
    public Long countuserbypub( @Param("id") Long id);
	

	@Query("SELECT p FROM Publication p where p.users =:id")
    public Long userbypub( @Param("id") Long id);
	

}
 