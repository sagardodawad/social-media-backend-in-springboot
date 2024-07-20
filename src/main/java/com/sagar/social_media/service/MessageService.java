package com.sagar.social_media.service;

import com.sagar.social_media.models.Chat;
import com.sagar.social_media.models.Message;
import com.sagar.social_media.models.User;

import java.util.List;

public interface MessageService {
    public Message createMessage(User user, Integer chatId, Message req) throws Exception;
    public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
