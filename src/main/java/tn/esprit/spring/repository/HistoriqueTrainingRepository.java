package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.HistoriqueTraining;
@Repository
public interface HistoriqueTrainingRepository  extends CrudRepository <HistoriqueTraining,Long>  {

	List<HistoriqueTraining> findByUser(User u);

}
