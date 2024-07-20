package com.sagar.social_media.controller;

import com.sagar.social_media.models.Chat;
import com.sagar.social_media.models.User;
import com.sagar.social_media.request.CreateChatRequest;
import com.sagar.social_media.service.ChatService;
import com.sagar.social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @PostMapping("/chats")
    public Chat createChat(@RequestBody CreateChatRequest req,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser=userService.findUserByJwt(jwt);
        Integer userId2=req.getUserId();
        Optional<User> user2=userService.findUserById(userId2);
        Chat chat=chatService.createChat(reqUser,user2.get());
        return  chat;
    }
    @GetMapping("/listOfChats")
    public List<Chat> findUserChat(@RequestHeader("Authorization") String jwt){
        User user=userService.findUserByJwt(jwt);

        List<Chat> chat=chatService.findUsersChat(user.getId());
        return  chat;
    }

}
