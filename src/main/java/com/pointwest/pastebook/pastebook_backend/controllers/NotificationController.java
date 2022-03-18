package com.pointwest.pastebook.pastebook_backend.controllers;
import com.pointwest.pastebook.pastebook_backend.config.JwtToken;
import com.pointwest.pastebook.pastebook_backend.models.NotifCardRequest;
import com.pointwest.pastebook.pastebook_backend.models.Notification;
import com.pointwest.pastebook.pastebook_backend.services.FriendRequestService;
import com.pointwest.pastebook.pastebook_backend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    JwtToken jwtToken;

    // accepting friend
    @RequestMapping(value="/api/notifications/seen", method = RequestMethod.POST)
    public ResponseEntity seenNotifications(
            @RequestHeader (value = "Authorization") String stringToken,
            @RequestBody Notification[] notifications
    ) {
        if(!stringToken.equals(null)) {
            String user_Id = jwtToken.getIdFromToken(stringToken);
            return notificationService.seenNotifications(notifications);
            //return notificationService.get5Notifications(Long.parseLong(user_Id));
        }
        //return friendService.acceptFriend(friendMap);
        return null;
    }

    @RequestMapping(value="/api/notifications/short", method = RequestMethod.GET)
    public List<NotifCardRequest> get5LatestNotifications(
            @RequestHeader (value = "Authorization") String stringToken
    ) {
        if(!stringToken.equals(null)) {
            String user_Id = jwtToken.getIdFromToken(stringToken);
            return notificationService.get5Notifications(Long.parseLong(user_Id));
        }
        //return friendService.acceptFriend(friendMap);
        return null;
    }

    @RequestMapping(value="/api/notifications/all", method = RequestMethod.GET)
    public List<NotifCardRequest> getAllMyNotifications(@RequestHeader (value = "Authorization") String stringToken) {

        if(!stringToken.equals(null)) {
            String user_Id = jwtToken.getIdFromToken(stringToken);
            return notificationService.getAllMyNotification(Long.parseLong(user_Id));
        }
        //return friendService.acceptFriend(friendMap);
        return null;
    }
}
