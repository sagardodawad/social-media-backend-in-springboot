package com.sagar.social_media.repository;

import com.sagar.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

   public User findByEmail(String email);
   @Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email like %:query%")
   public List<User> searchUser(@Param("query") String query);
}
