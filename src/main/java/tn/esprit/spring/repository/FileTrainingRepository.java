package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.FileTraining;

public interface FileTrainingRepository extends JpaRepository<FileTraining, String> {	

}
