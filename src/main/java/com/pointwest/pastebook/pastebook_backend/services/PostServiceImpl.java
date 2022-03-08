package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Friend;
import com.pointwest.pastebook.pastebook_backend.models.Post;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.repositories.FriendRepository;
import com.pointwest.pastebook.pastebook_backend.repositories.PostRepository;
import com.pointwest.pastebook.pastebook_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    // creating a post
    public ResponseEntity createPost(Post post, Long posterId, Long postedId) {
        // only authenticated users that are friends with a person can post to that person's account
        // this is tantamount to using a for-loop that will run through every record in the friends table, where the records that we're interested in are the records containing the 'posterId'

        ArrayList<Friend> friendsAllowedToPost = new ArrayList<>();

        for (Friend friend : friendRepository.findAll()) {
            if (friend.getRequester().getId() == postedId || friend.getRecipient().getId() == postedId) {
                // store into an array list
                friendsAllowedToPost.add(friend);
            }
        }
//
        // looping through the friendsAllowedToPost array where a condition inside the loop checks if the user is allowed to post to the user with id: postedId
        for (Friend friend : friendsAllowedToPost) {
           if (posterId == friend.getRequester().getId() || posterId == friend.getRecipient().getId()) {
               // THEN ALLOW THE USER TO POST!

               // creating user objects to connect them with the user_id foreign keys in the posts table
               User userWhoPosted = userRepository.findById(posterId).get();
               User userWhoGotPosted = userRepository.findById(postedId).get();

               Post postToCreate = new Post();

               postToCreate.setTitle(post.getTitle());
               postToCreate.setContent(post.getContent());
               postToCreate.setSenderUser(userWhoPosted);
               postToCreate.setReceiverUser(userWhoGotPosted);

               // getting 'Date' object and converting it to string
               LocalDateTime dateObject = LocalDateTime.now();
               DateTimeFormatter formatDateObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
               String formattedDate = dateObject.format(formatDateObj);

               postToCreate.setDatetimeCreated(formattedDate);

               postRepository.save(postToCreate);
               return new ResponseEntity("Post created successfully!", HttpStatus.CREATED);
           }
        }
        // this code will run after matapos yung loop (meaning, if the code below is executed, then walang nakita sa loop na id that satisfies the condition for the users that are allowed to post to user with id: posterId
        return new ResponseEntity("You are not allowed to post!", HttpStatus.CONFLICT);
    }
}
