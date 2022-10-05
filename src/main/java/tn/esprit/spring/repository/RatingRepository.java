package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Likes;
import tn.esprit.spring.entity.Rating;

@Repository

public interface RatingRepository extends JpaRepository<Rating, Long>{

    Optional<Rating> findByRateBy(String ratedby);
    
    
    @Query(value="select AVG(rate) from Rating R where R.publications_id=:pub", nativeQuery = true)
	float RateAVG(@Param("pub")long pub);

}
