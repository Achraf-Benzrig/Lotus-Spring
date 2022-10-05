package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.RendezVous;

@Repository
public interface RendezVousRepsitory extends CrudRepository<RendezVous, Long> {

}
