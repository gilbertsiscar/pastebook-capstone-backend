package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.NotifCardRequest;
import com.pointwest.pastebook.pastebook_backend.models.Notification;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificationService {
    List<NotifCardRequest> get5Notifications(Long user_id);
    ResponseEntity getAllMyNotification(Long user_id);
    ResponseEntity seenNotifications(Notification[] notifications);
}
