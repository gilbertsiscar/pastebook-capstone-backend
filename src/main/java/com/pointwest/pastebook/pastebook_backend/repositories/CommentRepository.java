package com.pointwest.pastebook.pastebook_backend.repositories;

import com.pointwest.pastebook.pastebook_backend.models.Comment;
import com.pointwest.pastebook.pastebook_backend.models.LikedPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Object> {
    @Query(value = "SELECT * FROM comments WHERE post_id= ?1 AND user_id = ?2",
            nativeQuery = true)
    Comment getCommentObject(Long post_id, Long user_id);

}
