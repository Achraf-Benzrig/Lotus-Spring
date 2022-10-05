package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.ReclamationType;
import tn.esprit.spring.entity.ReponseRec;

@Repository
public interface ReponseRecRepository extends CrudRepository<ReponseRec, Long>{

	@Query("SELECT DISTINCT rep.message FROM ReponseRec rep INNER JOIN rep.reclamation rec WHERE rec.type= :reclamationType ")
	 List<String> reponseSuggestion(@Param("reclamationType") ReclamationType reclamationType );
}
