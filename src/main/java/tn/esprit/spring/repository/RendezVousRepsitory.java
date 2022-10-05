package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.RendezVous;

@Repository
public interface RendezVousRepsitory extends CrudRepository<RendezVous, Long> {

	@Transactional
    @Modifying
    @Query("UPDATE RendezVous r " + "SET r.confirmed = TRUE WHERE r.code = ?1")
    int confirmRendezVous(String code);
	
	@Query("SELECT r FROM RendezVous r WHERE r.confirmed= 1")
	List<RendezVous> getConfirmedRendezVous();
}
