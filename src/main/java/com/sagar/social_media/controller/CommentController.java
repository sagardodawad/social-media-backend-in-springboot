package com.sagar.social_media.controller;

import com.sagar.social_media.models.Comment;
import com.sagar.social_media.models.User;
import com.sagar.social_media.service.CommentService;
import com.sagar.social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    UserService userService;
    @PostMapping("/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {
        User user=userService.findUserByJwt(jwt);
        Comment createdComment=commentService.createComment(comment,postId,user.getId());
        return createdComment;
    }
    @PutMapping("/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,@PathVariable Integer commentId) throws Exception {
        User user=userService.findUserByJwt(jwt);
        Comment likedComment=commentService.likeComment(commentId,user.getId());
        return likedComment;
    }
}
