package com.covoiturage.controller;

import com.covoiturage.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // POST /api/notifications/email/{userId}?sujet=...&contenue=...
    @PostMapping("/email/{userId}")
    public ResponseEntity<Void> notifierEmail(@PathVariable Long userId, @RequestParam String sujet, @RequestParam String contenue) {
        notificationService.notifierEmail(userId, sujet, contenue);
        return ResponseEntity.noContent().build();
    }
}
