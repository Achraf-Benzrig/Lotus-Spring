package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Dislike;
import tn.esprit.spring.entity.Likes;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.Reporting;

@Repository
@Transactional(readOnly = true)
public interface DislikesRepository extends JpaRepository<Dislike, Long>{

    Optional<Dislike> findByDislikedBy(String DislikeBy);
}
