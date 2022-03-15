package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.config.JwtToken;
import com.pointwest.pastebook.pastebook_backend.models.Post;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.repositories.PostRepository;
import com.pointwest.pastebook.pastebook_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired private PostRepository postRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private JwtToken jwtToken;

    @Override
    public Post createPost(Post post, String token) {
        Long authenticatedId = Long.parseLong(jwtToken.getIdFromToken(token));
        User user = userRepository.findById(authenticatedId).get();

        post.setUser(user);

        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            return post.get();
        } else {
            throw new RuntimeException("Error");
        }
    }

    @Override
    public Iterable<Post> getPostsFromUser(String stringToken) {
        User user = userRepository.findByEmail(jwtToken.getUsernameFromToken(stringToken));

        return user.getPosts();
    }

    @Override
    public Iterable<Post> getAllPost() {
        return this.postRepository.findAll();
    }
}
