package com.sagar.social_media.repository;

import com.sagar.social_media.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
 @Query("select p from Post p where p.user.id=:userId")

    List<Post> findPostByUserId(Integer userId);

    void deleteByUserId(Integer id);
}
