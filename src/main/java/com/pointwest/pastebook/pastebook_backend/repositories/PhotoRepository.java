package com.pointwest.pastebook.pastebook_backend.repositories;

import com.pointwest.pastebook.pastebook_backend.models.Photo;
import com.pointwest.pastebook.pastebook_backend.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Object> {
    //Optional<User> findByRequester_id (Long id);
}
