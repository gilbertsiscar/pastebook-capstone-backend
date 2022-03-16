package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Notification;
import com.pointwest.pastebook.pastebook_backend.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public ResponseEntity get5Notifications(Long user_id) {
        Iterable<Notification> notifications = notificationRepository.get5Notifications(user_id);
        return new ResponseEntity(notifications, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllMyNotification(Long user_id) {
        Iterable<Notification> notifications = notificationRepository.findAllOfMyNotification(user_id);
        return new ResponseEntity(notifications, HttpStatus.OK);
    }
}
