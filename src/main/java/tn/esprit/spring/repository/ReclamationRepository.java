package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import tn.esprit.spring.entity.Reclamation;
@CrossOrigin
@Repository
public interface ReclamationRepository extends CrudRepository<Reclamation, Long> {

}
