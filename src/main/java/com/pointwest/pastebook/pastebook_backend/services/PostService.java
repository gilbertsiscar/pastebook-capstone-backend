package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Post;

public interface PostService {

  Post createPost(Post post, String token);

  Post getPostById(Long id);

  Iterable<Post> getPostsFromUser(String stringToken);

  Iterable<Post> getAllPost();
}