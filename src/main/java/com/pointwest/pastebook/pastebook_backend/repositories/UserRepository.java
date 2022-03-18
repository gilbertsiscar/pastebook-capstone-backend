package com.pointwest.pastebook.pastebook_backend.repositories;

import com.pointwest.pastebook.pastebook_backend.models.LikedPost;
import com.pointwest.pastebook.pastebook_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Object> {
    @Query(value = "SELECT * FROM user WHERE profile_url = ?1",
            nativeQuery = true)
    User getUserProfileByUrl(String profileUrl);

    @Query(value = "SELECT * FROM `users` WHERE mobile_number = ?1",nativeQuery = true)
    Optional<User> getUserDetailsByMobile(String mobile);

    User findByEmail(String email);


}