package tn.esprit.spring.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.NotificationUser;

@Repository
public interface NotificationUserRepository extends JpaRepository<NotificationUser, Long>{
    @Transactional
    @Modifying
    @Query("UPDATE NotificationUser a " + "SET a.old = TRUE , a.readAt = ?1 WHERE a.id = ?2")
    void readNotificationUser(LocalDateTime date,Long id);

}
