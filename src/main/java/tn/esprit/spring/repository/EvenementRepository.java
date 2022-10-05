package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long>{
	
	List<Evenement> findByLieux(String lieux);
}
