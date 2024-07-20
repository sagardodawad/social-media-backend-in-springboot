package com.sagar.social_media.controller;

import com.sagar.social_media.models.Message;
import com.sagar.social_media.models.User;
import com.sagar.social_media.service.MessageService;
import com.sagar.social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CreateMessage {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @PostMapping("/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId
    ) throws Exception {
        User user=userService.findUserByJwt(jwt);
        Message message=messageService.createMessage(user,chatId,req);

        return message;
    }
    @GetMapping("/messages/chat/{chatId}")
    public List<Message> findChatMessage( @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId
    ) throws Exception {
        User user=userService.findUserByJwt(jwt);
        List<Message> message=messageService.findChatsMessages(chatId);

        return message;
    }
}
