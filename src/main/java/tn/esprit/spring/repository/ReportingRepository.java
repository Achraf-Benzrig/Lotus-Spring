package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Reporting;

@Repository
public interface ReportingRepository extends JpaRepository<Reporting, Long>{

}
