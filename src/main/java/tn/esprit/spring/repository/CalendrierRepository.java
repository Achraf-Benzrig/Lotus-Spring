package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Calendrier;

@Repository
public interface CalendrierRepository extends CrudRepository<Calendrier, Long>{

}
