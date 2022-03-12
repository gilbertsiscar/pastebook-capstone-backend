package com.pointwest.pastebook.pastebook_backend.repositories;

import com.pointwest.pastebook.pastebook_backend.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Object> {
}
