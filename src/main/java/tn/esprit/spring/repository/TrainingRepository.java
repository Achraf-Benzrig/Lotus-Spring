package tn.esprit.spring.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Interest;
import tn.esprit.spring.entity.Training;


@Repository
public interface TrainingRepository extends CrudRepository <Training,Long> {
	@Query("select t from Training t where t.intereset In ?1")
	List<Training>suggestedTrainings(List<Interest>userInterests);
	
	List<Training> findBytitle(String title);
	List<Training> findByformateur(String formateur);

	List<Training> findByTitleContaining(String text);

}
