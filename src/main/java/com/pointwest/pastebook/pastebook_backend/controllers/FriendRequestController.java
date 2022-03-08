package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.services.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    // send friend request
    @RequestMapping(value="/friendRequests/{senderId}/{receiverId}", method = RequestMethod.POST)
    public ResponseEntity<Object> sendFriendRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        return friendRequestService.sendFriendRequest(senderId, receiverId);
    }

    // get friend requests
    @RequestMapping(value="/friendRequests/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFriendRequests(@PathVariable Long id) {
        return friendRequestService.getFriendRequests(id);
    }

    // delete friend request
    @RequestMapping(value="/friendRequests/{senderId}/{receiverId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> cancelFriendRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        return friendRequestService.cancelFriendRequest(senderId, receiverId);
    }
}
