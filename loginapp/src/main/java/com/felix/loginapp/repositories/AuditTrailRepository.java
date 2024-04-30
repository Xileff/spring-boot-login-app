package com.felix.loginapp.repositories;

import com.felix.loginapp.entities.AuditTrail;
import com.felix.loginapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrail, Long> {
    List<AuditTrail> findByUserOrderByCreatedAtDesc(User user);
}
