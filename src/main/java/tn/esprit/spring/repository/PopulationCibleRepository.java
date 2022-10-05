package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.PopulationCible;

@Repository
public interface PopulationCibleRepository extends CrudRepository<PopulationCible, Long>{

}
