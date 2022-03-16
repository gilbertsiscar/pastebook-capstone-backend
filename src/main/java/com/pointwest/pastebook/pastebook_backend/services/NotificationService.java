package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Notification;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    ResponseEntity get5Notifications(Long user_id);
    ResponseEntity getAllMyNotification(Long user_id);
}
