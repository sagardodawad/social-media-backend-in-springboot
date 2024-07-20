package com.sagar.social_media.repository;

import com.sagar.social_media.models.Chat;
import com.sagar.social_media.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    @Query("select c from Chat c join c.users u where u.id = :userId")
    public List<Chat> findByUserId(Integer userId);
  @Query("select c from Chat c where :user Member of c.users and :reqUser member of c.users")
    public Chat findChatByUsersId(@Param("user")User user,@Param("reqUser") User reqUser);


}
