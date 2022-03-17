package com.pointwest.pastebook.pastebook_backend.repositories;

import com.pointwest.pastebook.pastebook_backend.models.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Object> {
    @Query(value = "SELECT * FROM `notifications`\n" +
            "INNER JOIN post ON post.id = notifications.post_id\n" +
            "WHERE post.user_id = ?1\n" +
            "ORDER BY notifications.datetime_created DESC\n",
            nativeQuery = true)
    Iterable<Notification> findAllOfMyNotification (Long user_id);

    @Query(value = "SELECT * FROM `notifications`\n" +
            "INNER JOIN post ON post.id = notifications.post_id\n" +
            "WHERE post.user_id = ?1\n" +
            "ORDER BY notifications.datetime_created DESC\n" +
            "LIMIT 5",
    nativeQuery = true)
    Iterable<Notification> get5Notifications (Long user_id);
}
