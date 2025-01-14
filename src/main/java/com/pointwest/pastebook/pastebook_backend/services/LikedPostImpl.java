package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.config.JwtToken;
import com.pointwest.pastebook.pastebook_backend.models.LikedPost;
import com.pointwest.pastebook.pastebook_backend.models.Post;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.repositories.LikedPostRepository;
import com.pointwest.pastebook.pastebook_backend.repositories.PostRepository;
import com.pointwest.pastebook.pastebook_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikedPostImpl implements LikedPostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikedPostRepository likedPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtToken jwtToken;

    @Override
    public boolean likePost(Long postId, String token) {
        Optional<Post> postToLike = postRepository.findById(postId);
        Long authenticatedId = Long.parseLong(jwtToken.getIdFromToken(token));
        User user = userRepository.findById(authenticatedId).get();

        if(postToLike.isPresent()){
            LikedPost commenceLike = new LikedPost();
            commenceLike.setPost(postToLike.get());
            commenceLike.setUser(user);
            postToLike.get().getLikes().add(commenceLike);
            postRepository.save(postToLike.get());
            likedPostRepository.save(commenceLike);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ResponseEntity unlikePost(Long postId, String token) {
        Optional<Post> postToUnLike = postRepository.findById(postId);
        if(postToUnLike.isPresent()){
            Long authenticatedId = Long.parseLong(jwtToken.getIdFromToken(token));
            User user = userRepository.findById(authenticatedId).get();

            LikedPost unlike = likedPostRepository.getLikePostToUnlike(postToUnLike.get().getId(), user.getId());

            postToUnLike.get().getLikes().remove(unlike);
            postRepository.save(postToUnLike.get());
            likedPostRepository.delete(unlike);
            return new ResponseEntity("Unliked post", HttpStatus.OK);
        }else{
            return new ResponseEntity("Post not found!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Integer getLikes(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post.getLikes().size();
    }

}