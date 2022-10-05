package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository <Offer, Long>{

    @Query("SELECT f FROM Offer f WHERE f.Title LIKE %?1%" //to search
            + " OR f.Place LIKE %?1%"
            + " OR f.Domain LIKE %?1%")
            //+ " OR CONCAT(p.price, '') LIKE %?1%")
    public List<Offer> search(String keyword);
    
    @Query("SELECT f FROM Offer f  where f.Title = :Title")
    public List<Offer> FindOfferByTitle(@Param("Title") String title);
    
    @Query("SELECT f FROM Offer f join f.user u where u.profession= :profession  or u.niveauetude= :niveauetude  or u.description= :description or u.adress= :adress")
    public List<Offer> suggestedOffer( @Param("profession") String profession , @Param("niveauetude") String niveauetude 
    		, @Param("description") String description ,@Param("adress")  String adress );
    

@Query("SELECT t.Domain  FROM Offer t JOIN t.user u GROUP BY u.profession ORDER BY u.profession DESC")
List<Object[]> countDestinationByDomainUser();


}
