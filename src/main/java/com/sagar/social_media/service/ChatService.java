package com.sagar.social_media.service;

import com.sagar.social_media.models.Chat;
import com.sagar.social_media.models.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser,User user2);
    public Chat findChatById(Integer chatId) throws Exception;
    public List<Chat> findUsersChat(Integer userId);
}
