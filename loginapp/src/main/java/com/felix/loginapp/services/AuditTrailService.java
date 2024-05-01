package com.felix.loginapp.services;

import com.felix.loginapp.entities.AuditTrail;
import com.felix.loginapp.entities.User;
import com.felix.loginapp.entities.enums.Actions;
import com.felix.loginapp.repositories.AuditTrailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditTrailService {
    private final AuditTrailRepository auditTrailRepository;

    public void insertAuditTrail(User user, Actions action, String route, String information) {
        try {
            AuditTrail auditTrail = new AuditTrail();
            auditTrail.setUser(user);
            auditTrail.setAction(action);
            auditTrail.setRoute(route);
            auditTrail.setCreatedAt(LocalDateTime.now());
            auditTrail.setInformation(information);
            auditTrailRepository.save(auditTrail);
        } catch (Exception e) {
            System.out.println("AuditTrailService Error : " + e.getMessage());
        }
    }
}
