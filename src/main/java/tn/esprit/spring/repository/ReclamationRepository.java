package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import tn.esprit.spring.entity.Reclamation;
@CrossOrigin
@Repository
public interface ReclamationRepository extends CrudRepository<Reclamation, Long> {

	@Query("select r from Reclamation r where r.traitee= 1")
	List<Reclamation>findAllTraitees();
	
	
	@Query("select r from Reclamation r where r.traitee= 0")
	List<Reclamation>findAllNonTraitees();
}
