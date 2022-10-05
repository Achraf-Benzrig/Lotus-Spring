package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository <Question, Long>{

}
