package com.pointwest.pastebook.pastebook_backend.repositories;

import com.pointwest.pastebook.pastebook_backend.models.LikedPost;
import com.pointwest.pastebook.pastebook_backend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Object> {
    @Query(value = "SELECT * FROM user WHERE profile_url = ?1",
            nativeQuery = true)
    User getUserProfileByUrl(String profileUrl);
    User findByEmail(String email);
}
