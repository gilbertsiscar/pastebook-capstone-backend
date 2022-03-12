package com.pointwest.pastebook.pastebook_backend.repositories;

import com.pointwest.pastebook.pastebook_backend.models.LikedPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedPostRepository extends CrudRepository<LikedPost, Object> {
}
