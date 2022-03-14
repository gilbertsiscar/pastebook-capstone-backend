package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Friend;
import com.pointwest.pastebook.pastebook_backend.models.FriendRequest;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.repositories.FriendRepository;
import com.pointwest.pastebook.pastebook_backend.repositories.FriendRequestRepository;
import com.pointwest.pastebook.pastebook_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private FriendRepository friendRepository;
    //
    // send friend request

    public ResponseEntity sendFriendRequest(HashMap<String, Object> friendRequestMap)  {

        String senderIdStr = friendRequestMap.get("senderId").toString();
        String receiverIdStr = friendRequestMap.get("receiverId").toString();

        Long senderId = Long.parseLong(senderIdStr);
        Long receiverId = Long.parseLong(receiverIdStr);

//        //FOR THE FRIEND REQUEST, NOTE: CAN'T SEND ANOTHER FRIEND REQUEST TO THE SAME PERSON IF THE REQUEST IS STILL EXISTING
//        for (FriendRequest friendRequest : friendRequestRepository.findAll()) {
//            if ((friendRequest.getSender().getId() == senderId && friendRequest.getReceiver().getId() == receiverId) || friendRequest.getSender().getId() == receiverId && friendRequest.getReceiver().getId() == senderId ) {
//                return new ResponseEntity("You're already friends!", HttpStatus.CONFLICT);
//            }
//        }


        // loop through every record in the friends table and if the friend.getRequester.getId() == senderId &&
        // friend.getRecipient.getId() == receiverId => print "You're already friends!"
        for (Friend friend : friendRepository.findAll()) {
            if ((friend.getRequester().getId() == senderId && friend.getRecipient().getId() == receiverId) || friend.getRequester().getId() == receiverId && friend.getRecipient().getId() == senderId ) {
                return new ResponseEntity("You're already friends!", HttpStatus.CONFLICT);

            }
        }

        //FOR THE FRIEND REQUEST, NOTE: ALREADY FRIENDS CAN'T SEND FRIEND REQUEST ANYMORE TO EACH OTHER! (USE CONDITIONAL STATEMENT)

        // loop through every record in the friends table and if the friend.getRequester.getId() == senderId &&
        // friend.getRecipient.getId() == receiverId => print "You're already friends!"
        for (Friend friend : friendRepository.findAll()) {
            if ((friend.getRequester().getId() == senderId && friend.getRecipient().getId() == receiverId) || friend.getRequester().getId() == receiverId && friend.getRecipient().getId() == senderId ) {
                return new ResponseEntity("You're already friends!", HttpStatus.CONFLICT);

            }
        }

        //FOR THE FRIEND REQUEST, NOTE: IF USER A HAS ALREADY SENT A REQUEST TO USER B, USER A CAN'T ADD USER B AGAIN, UNLESS THE RECORD IS REMOVED FROM THE friends_request table
        for (FriendRequest friendRequest : friendRequestRepository.findAll()) {
            if (friendRequest.getSender().getId() == senderId && friendRequest.getReceiver().getId() == receiverId) {
                return new ResponseEntity("You already added this person!", HttpStatus.CONFLICT);
            }
        }

        // otherwise, if they're not yet friends, automatic na mag run yung code below (automatic rin mag stop yung function since we have the return statement inside the for-loop)

        // getting the sender and receiver using the path variable ids
        User sender = userRepository.findById(senderId).get();
        User receiver = userRepository.findById(receiverId).get();

        // getting 'Date' object and converting it to string
        LocalDateTime dateObject = LocalDateTime.now();
        DateTimeFormatter formatDateObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String formattedDate = dateObject.format(formatDateObj);

        // creating a record in the friend_requests table where the record contains the senderId and the receiverId
        FriendRequest newFriendRequest = new FriendRequest(true, formattedDate, sender, receiver);

        // save the friendRequest
        friendRequestRepository.save(newFriendRequest);

        return new ResponseEntity("Friend request sent to " + receiver.getFirstName(), HttpStatus.CREATED);
    }

    // get friend requests
    public ResponseEntity getFriendRequests(Long id) {
        // Array List for getting all the FriendRequest of a user that has id: id
        ArrayList<User> friendRequester = new ArrayList<>();

        // need to display the list of all friend requests for the user with the id: id

        // loop through every record in the friend_requests table and get all the records for the id: id
        for (FriendRequest friendRequest : friendRequestRepository.findAll()) {
            // need to get the id, which is coming from the first property of FriendRequest model
            if (friendRequest.getReceiver().getId() == id) {
                // add the User (which is the sender) into an Array List, which we will print out later
                friendRequester.add(friendRequest.getSender());
            }
        }
        return new ResponseEntity(friendRequester, HttpStatus.OK);
    }

//    public ResponseEntity cancelFriendRequest(HashMap<String, Object> friendRequestMap) {
//        // by cancelling the friend request, we are removing the particular record in the friend_requests table
//
//        String senderIdStr = friendRequestMap.get("senderId").toString();
//        String receiverIdStr = friendRequestMap.get("receiverId").toString();
//
//        Long senderId = Long.parseLong(senderIdStr);
//        Long receiverId = Long.parseLong(receiverIdStr);
//
//        for (FriendRequest friendRequest : friendRequestRepository.findAll()) {
//            if (friendRequest.getSender().getId() == senderId && friendRequest.getReceiver().getId() == receiverId) {
//                friendRequestRepository.deleteById(friendRequest.getId());
//                return new ResponseEntity("Friend request deleted", HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity("Exception message", HttpStatus.OK);
//    }

    // get one friend request
    public ResponseEntity getOneFriendRequest(Long senderId, Long receiverId) {
        for (FriendRequest friendRequest : friendRequestRepository.findAll()) {
            if (senderId == friendRequest.getSender().getId() && receiverId == friendRequest.getReceiver().getId()) {
                Optional<FriendRequest> optionalFriendRequest = Optional.of(friendRequest);
                return new ResponseEntity(optionalFriendRequest, HttpStatus.OK);
            }
        }
        Optional<FriendRequest> noFriendRequest = Optional.of(new FriendRequest());
        return new ResponseEntity(noFriendRequest, HttpStatus.OK);
    }

    // delete one friend request
    public ResponseEntity cancelFriendRequest(Long senderId, Long receiverId) {
        // by cancelling the friend request, we are removing the particular record in the friend_requests table

        for (FriendRequest friendRequest : friendRequestRepository.findAll()) {
            if (friendRequest.getSender().getId() == senderId && friendRequest.getReceiver().getId() == receiverId) {
                friendRequestRepository.deleteById(friendRequest.getId());
                return new ResponseEntity("Friend request deleted", HttpStatus.OK);
            }
        }
        return new ResponseEntity("Exception message", HttpStatus.OK);
    }

}
