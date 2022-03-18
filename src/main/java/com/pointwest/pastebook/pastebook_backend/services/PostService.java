package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Post;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface PostService {
    Post createPost(Post post, String token);

    Post createPost(Post post, String token, List<Long> id);

    Post getPostById(Long id);

    List<Post> getPostsFromUser(String stringToken);

    Iterable<Post> getAllPost();

    List<Post> getPostOfFriends(Long id, Integer page, Integer size);

    Page<Post> getPostsPagination(Integer page, Integer size);

    Post updatePost(Long id, Post post);

    void deletePost(Long id);
}