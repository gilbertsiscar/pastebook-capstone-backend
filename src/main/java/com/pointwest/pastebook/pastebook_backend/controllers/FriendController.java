package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
public class FriendController {

    @Autowired
    private FriendService friendService;

    // accepting friend
    @RequestMapping(value="/api/friends", method = RequestMethod.POST)
    public ResponseEntity<Object> acceptFriend(@RequestBody HashMap<String, Object> friendMap) {
        return friendService.acceptFriend(friendMap);
    }

    // deleting friend
    @RequestMapping(value="/api/friends/{requesterId}/{recipientId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteFriend(@PathVariable Long requesterId, @PathVariable Long recipientId) {
        return friendService.deleteFriend(requesterId, recipientId);
    }


    // get all friends given a particular page id
    @RequestMapping(value="/api/friends/{pageId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFriends(@PathVariable Long pageId) {
        return friendService.getFriends(pageId);
    }

    // get one friend
    @RequestMapping(value="/api/friends/{requesterId}/{recipientId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOneFriend(@PathVariable Long requesterId, @PathVariable Long recipientId) {
        return friendService.getOneFriend(requesterId, recipientId);
    }

}
