package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.NotifCardRequest;
import com.pointwest.pastebook.pastebook_backend.models.Notification;
import com.pointwest.pastebook.pastebook_backend.repositories.NotificationRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public List<NotifCardRequest> get5Notifications(Long user_id) {
        List<NotifCardRequest> notifs = new ArrayList<>();
        Iterable<Notification> notifications = notificationRepository.get5Notifications(user_id);
        Iterator<Notification> iter = notifications.iterator();

        for(Notification notif: notifications){
            NotifCardRequest card = new NotifCardRequest(
                    notif.getId(),notif.getSender().getFirstName(),
                    notif.getContent(),notif.getPost().getId(),
                    notif.isReadStatus(),notif.getDatetimeCreated()
            );
            notifs.add(card);
        }
        return notifs;
    }

    @Override
    public ResponseEntity getAllMyNotification(Long user_id) {

        Iterable<Notification> notifications = notificationRepository.findAllOfMyNotification(user_id);
        return new ResponseEntity(notifications, HttpStatus.OK);
    }

    @Override
    public ResponseEntity seenNotifications(Notification[] notifications) {
        for (Notification notification: notifications
             ) {
            Notification notifToUpdate = notificationRepository.findById(notification.getId()).get();
            notifToUpdate.setReadStatus(true);
            notificationRepository.save(notifToUpdate);
        }
        return new ResponseEntity("Notifications seen", HttpStatus.OK);
    }
}
