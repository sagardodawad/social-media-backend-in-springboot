package com.sagar.social_media.service;

import com.sagar.social_media.models.Chat;
import com.sagar.social_media.models.Message;
import com.sagar.social_media.models.User;
import com.sagar.social_media.repository.ChatRepository;
import com.sagar.social_media.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService{
@Autowired
    private ChatRepository chatRepository;
  @Autowired
    private MessageRepository messageRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private ChatService chatService;

    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {

        Chat chat=chatService.findChatById(chatId);
        Message message=new Message();
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());
    Message savedMessage=messageRepository.save(message);
    chat.getMessages().add(savedMessage);
 chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {

        Chat chat=chatService.findChatById(chatId);
        if (chat!=null) return messageRepository.findByChatId(chatId);
     throw new Exception("Chat not found");
    }
}
