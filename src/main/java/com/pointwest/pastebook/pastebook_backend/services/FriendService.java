package com.pointwest.pastebook.pastebook_backend.services;

import org.springframework.http.ResponseEntity;

public interface FriendService {

    // accept friend request (based on the FriendRequest model) -> essentially creating a new record in the friends table
    // also, has to delete the entire record on the friend_requests table using the id of the accepted user
    ResponseEntity acceptFriend(Long requesterId, Long recipientId);

    // delete friend
    ResponseEntity deleteFriend(Long requesterId, Long recipientId);

}
