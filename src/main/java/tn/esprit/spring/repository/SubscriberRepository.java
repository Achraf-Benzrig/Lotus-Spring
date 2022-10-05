package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long>{

}
