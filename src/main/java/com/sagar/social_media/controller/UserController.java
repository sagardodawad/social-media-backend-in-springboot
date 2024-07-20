package com.sagar.social_media.controller;

import com.sagar.social_media.exceptions.UserException;
import com.sagar.social_media.models.User;
import com.sagar.social_media.repository.PostRepository;
import com.sagar.social_media.repository.UserRepository;
import com.sagar.social_media.service.PostService;
import com.sagar.social_media.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    PostRepository postRepository;
    @GetMapping("/api/users")
    public List<User> getUsers(){
        List<User> users=userRepository.findAll();
        return users;
    }

    @GetMapping("/api/users/{userId}")
    public Optional<User> getUsersById(@PathVariable("userId") Integer id) throws UserException {
       return userService.findUserById(id);
    }


    @PutMapping("/api/updateUser")
    public Optional<User> updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws UserException {

        User reqUser=userService.findUserByJwt(jwt);

User user1=userService.updateUser(user,reqUser.getId());
return Optional.ofNullable(user1);
    }

    @DeleteMapping("/api/deleteUser/{userId}")
    @Transactional
    public String deleteUser(@PathVariable("userId") Integer id) throws UserException {
     Optional<User> user =userRepository.findById(id);
if (user.isPresent()){
    postRepository.deleteByUserId(id);
    userRepository.delete(user.get());
        return "User deleted successfully with id "+ id;
    }

    throw new UserException("user is not present with id "+id);
    }

    @PutMapping("/api/users/follow/{userId2}")
    public User followUsers(@RequestHeader("Authorization") String jwt,@PathVariable Integer userId2) throws UserException {
        User user1=userService.findUserByJwt(jwt);
       Integer userId1=user1.getId();
        userService.followUser(userId1,userId2);
        return userRepository.getById(userId1);
    }
    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users=userService.searchUser(query);
        return users;
    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){
//        System.out.println(jwt);
        return userService.findUserByJwt(jwt);
    }
}
