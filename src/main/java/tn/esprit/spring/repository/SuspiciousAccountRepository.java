package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Subscriber;
import tn.esprit.spring.entity.SuspiciousAccount;

@Repository
public interface SuspiciousAccountRepository extends JpaRepository<SuspiciousAccount, Long>{

}
