package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.ToDoList;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoList,Long>{
	List<ToDoList> findByUserEmail(String email);

}
