package com.pointwest.pastebook.pastebook_backend.services;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface FriendRequestService {

    // send friend request
    ResponseEntity sendFriendRequest(HashMap<String, Object> friendRequestMap);

    // get friend requests
    ResponseEntity getFriendRequests(Long id);

    // get one friend request
    ResponseEntity getOneFriendRequest(Long senderId, Long receiverId);

    // delete one friend request
    ResponseEntity cancelFriendRequest(Long senderId, Long receiverId);
}
