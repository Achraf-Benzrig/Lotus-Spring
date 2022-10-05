package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long>{

}
