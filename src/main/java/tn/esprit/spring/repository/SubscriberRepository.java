package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long>{

    @Query("SELECT COUNT(id) AS Number, MONTH(created_at) AS MONTH FROM Subscriber GROUP BY YEAR(created_at), MONTH(created_at)")
	List<Object[]> countSubscriberByMonth();
	

}
