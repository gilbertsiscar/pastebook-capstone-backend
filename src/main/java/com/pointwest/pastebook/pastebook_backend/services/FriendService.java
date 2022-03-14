package com.pointwest.pastebook.pastebook_backend.services;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface FriendService {

    // accept friend request (based on the FriendRequest model) -> essentially creating a new record in the friends table
    // also, has to delete the entire record on the friend_requests table using the id of the accepted user
    ResponseEntity acceptFriend(HashMap<String, Object> friendMap);

    // delete friend
    ResponseEntity deleteFriend(Long requesterId, Long recipientId);

    // get all friends given a particular page id
    ResponseEntity getFriends(Long pageId);

    // get one friend
    ResponseEntity getOneFriend(Long requesterId, Long recipientId);

}
