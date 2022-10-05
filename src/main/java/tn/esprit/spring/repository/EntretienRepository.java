package tn.esprit.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Entretien;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long>{

}
