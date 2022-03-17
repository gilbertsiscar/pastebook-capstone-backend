package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    Post createPost(Post post, String token);

    Post createPost(Post post, String token, List<Long> id);

    Post getPostById(Long id);

    Iterable<Post> getPostsFromUser(String stringToken);

    Iterable<Post> getAllPost();

    Page<Post> getPostsPagination(Integer page, Integer size);

    Post updatePost(Long id, Post post);

    void deletePost(Long id);
}