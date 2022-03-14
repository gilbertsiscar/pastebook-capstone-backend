package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.config.JwtToken;
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
import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private JwtToken jwtToken;

    // creating a post
    public ResponseEntity createPost(Post post, String token, ArrayList<String> taggedIds) {
        // only authenticated users that are friends with a person can post to that person's account
        // this is tantamount to using a for-loop that will run through every record in the friends table, where the records that we're interested in are the records containing the 'posterId'

        Long authenticatedId = Long.parseLong(jwtToken.getIdFromToken(token));
        User tokenUser = userRepository.findById(authenticatedId).get();

        //Long friendId = post.getPostOwner().getId();


        //System.out.println();
        // If posting to self
//        if(post.getPostOwner().getId() == tokenUser.getId()){
//            //post to owner
////            LocalDateTime dateObject = LocalDateTime.now();
////            DateTimeFormatter formatDateObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
////            String formattedDate = dateObject.format(formatDateObj);
//
////            post.setDatetimeCreated(formattedDate);
//            //post.setPostOwner(tokenUser);
//            postRepository.save(post);
//            //return new ResponseEntity("Post created successfully!", HttpStatus.CREATED);
//
//        }
        post.setPostOwner(tokenUser);
        ArrayList<User> taggedUsers = new ArrayList();
        for (String id: taggedIds) {
            try
            {
                taggedUsers.add(userRepository.findById(Long.parseLong(id)).get());
            }catch (NoSuchElementException e){
                // Tagged user is not a friend
                System.err.println(e);
                //return new ResponseEntity("You guys are not friends!", HttpStatus.UNAUTHORIZED);
            }
        }

        // not sure if to pre save

        post.getTaggedUsers().addAll(taggedUsers);
        postRepository.save(post);

        return new ResponseEntity("Post created successfully!", HttpStatus.CREATED);

//        for (Friend friend : friendRepository.findAll()) {
//            if (friend.getRequester().getId() == friendId && friend.getRecipient().getId() == tokenUser.getId()
//                ||
//                friend.getRequester().getId() == tokenUser.getId() && friend.getRecipient().getId() == friendId
//            ) {
//                // store into an array list
//                //post.se(tokenUser);
//                postRepository.save(post);
//                return new ResponseEntity("Post created successfully!", HttpStatus.CREATED);
//            }
//        }


    }

    @Override
    public Iterable<Post> getPostsFromUser(String stringToken) {
        User user = userRepository.findByEmail(jwtToken.getUsernameFromToken(stringToken));

        return user.getPosts();
    }

    @Override
    public Iterable<Post> getTaggedPosts(String stringToken) {
        User user = userRepository.findByEmail(jwtToken.getUsernameFromToken(stringToken));
        return user.getTaggedpost();
    }

    @Override
    public Iterable<Post> getAllPostRelatedToUser(Long userId, String token) {
        User user = userRepository.findByEmail(jwtToken.getUsernameFromToken(token));
        Long authenticatedId = user.getId();

        //  Check if authorized, owner's or friend's post
        // authenticatedId = userId;
        // boolean areFriends(userId1, userId2;)



        // Change this into custom queries, so we should only get 10 at a time
        // Reduce the amount of data response
       return new HashSet<>() {
           {
               addAll(user.getPosts());
               addAll(user.getTaggedpost());
           }
       };
    }

    // getting all posts of a particular user
//    public ResponseEntity getPostsFromUser(Long visitorId, Long ownerId) {
//
//        ArrayList<Friend> friendsAllowedToSeePosts = new ArrayList<>();
//        ArrayList<Post> postsToDisplay = new ArrayList<>();
//        ArrayList<Post> newestPostsToDisplay = new ArrayList<>();
//
//        // Code for when the user is retrieving his own post (ADD THIS)
//
//        for (Friend friend : friendRepository.findAll()) {
//            // only friends and the owner of the account can see the posts
//            if (friend.getRequester().getId() == ownerId || friend.getRecipient().getId() == ownerId) {
//                // store into an array list
//                friendsAllowedToSeePosts.add(friend);
//            }
//        }
//
//        for (Friend friend : friendsAllowedToSeePosts) {
//            if (visitorId == friend.getRequester().getId() || visitorId == friend.getRecipient().getId()) {
//
//                // loop through postRepository.findAll() and store all posts in an array list with user id == ownerId
//                for (Post post : postRepository.findAll()) {
////                    if (post.getR().getId() == ownerId) {
////                        postsToDisplay.add(post);
////                    }
//                }
//
//                // for displaying first the newest posts
//                for (int i = postsToDisplay.size() - 1; i > -1; i--) {
//                    newestPostsToDisplay.add(postsToDisplay.get(i));
//                }
//
//                return new ResponseEntity(newestPostsToDisplay, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity("You are not allowed to see the post!", HttpStatus.CONFLICT);
//    }
//
//    @Override
//    public ResponseEntity getPostsRelatedToUser(String token) {
//        return null;
//    }
}
