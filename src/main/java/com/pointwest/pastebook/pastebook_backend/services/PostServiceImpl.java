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

        // Code for when the user is creating his own post (and when he doesn't have any friends yet, e.g. no record in the friends table)
        User user1 = new User();
        User user2 = new User();

        user1.setId(posterId);
        user2.setId(postedId);

        if (user1.getId() == user2.getId()) {

            Post postToCreate = new Post();

            postToCreate.setTitle(post.getTitle());
            postToCreate.setContent(post.getContent());
            postToCreate.setSenderUser(userRepository.findById(posterId).get());
            postToCreate.setReceiverUser(userRepository.findById(postedId).get());

            // getting 'Date' object and converting it to string
            LocalDateTime dateObject = LocalDateTime.now();
            DateTimeFormatter formatDateObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
            String formattedDate = dateObject.format(formatDateObj);

            postToCreate.setDatetimeCreated(formattedDate);

            postRepository.save(postToCreate);
            return new ResponseEntity("Post created successfully!", HttpStatus.CREATED);
        }

        for (Friend friend : friendRepository.findAll()) {
            if (friend.getRequester().getId() == postedId || friend.getRecipient().getId() == postedId) {
                // store into an array list
                friendsAllowedToPost.add(friend);
            }
        }

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

    // getting all posts of a particular user
    public ResponseEntity getPostsFromUser(Long visitorId, Long ownerId) {

        ArrayList<Friend> friendsAllowedToSeePosts = new ArrayList<>();
        ArrayList<Post> postsToDisplay = new ArrayList<>();
        ArrayList<Post> newestPostsToDisplay = new ArrayList<>();

        // Code for when the user is retrieving his own post (ADD THIS)

        for (Friend friend : friendRepository.findAll()) {
            // only friends and the owner of the account can see the posts
            if (friend.getRequester().getId() == ownerId || friend.getRecipient().getId() == ownerId) {
                // store into an array list
                friendsAllowedToSeePosts.add(friend);
            }
        }

        for (Friend friend : friendsAllowedToSeePosts) {
            if (visitorId == friend.getRequester().getId() || visitorId == friend.getRecipient().getId()) {

                // loop through postRepository.findAll() and store all posts in an array list with user id == ownerId
                for (Post post : postRepository.findAll()) {
                    if (post.getReceiverUser().getId() == ownerId) {
                        postsToDisplay.add(post);
                    }
                }

                // for displaying first the newest posts
                for (int i = postsToDisplay.size() - 1; i > -1; i--) {
                    newestPostsToDisplay.add(postsToDisplay.get(i));
                }

                return new ResponseEntity(newestPostsToDisplay, HttpStatus.OK);
            }
        }
        return new ResponseEntity("You are not allowed to see the post!", HttpStatus.CONFLICT);
    }
}
