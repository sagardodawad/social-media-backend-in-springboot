package com.sagar.social_media.controller;

import com.sagar.social_media.models.Post;
import com.sagar.social_media.models.User;
import com.sagar.social_media.response.ApiResponse;
import com.sagar.social_media.service.PostService;
import com.sagar.social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String jwt) throws Exception {
        User user1=userService.findUserByJwt(jwt);
        Integer userId=user1.getId();

        Post createdPost=postService.createNewPost(post,userId);
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user1=userService.findUserByJwt(jwt);
        Integer userId=user1.getId();
      String message= postService.deletePost(postId,userId);
      ApiResponse response=new ApiResponse(message,true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
      Post post=postService.findPostById(postId);
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);

    }

    @GetMapping("/posts/user/{userId}")
    public  ResponseEntity<List<Post>> findUsersPost( @PathVariable Integer userId){
        List<Post> posts=postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }
    @GetMapping("/allposts")
    public  ResponseEntity<List<Post>> findAllPosts(){
        List<Post> posts=postService.findAllPost();
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<User> savedPostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user1=userService.findUserByJwt(jwt);
        Integer userId=user1.getId();
        User user=postService.savedPost(postId,userId);
        return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
    }
    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception {
        User user1=userService.findUserByJwt(jwt);
        Integer userId=user1.getId();
        Post post=postService.likePost(postId,userId);
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

}
