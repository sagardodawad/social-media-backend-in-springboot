package com.sagar.social_media.service;

import com.sagar.social_media.exceptions.UserException;
import com.sagar.social_media.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User registerUser(User user);
    public Optional<User> findUserById(Integer userId) throws UserException;
    public User findUserByEmail(String Email);
    public User followUser(Integer userId1,Integer userId2) throws UserException;
    public User updateUser(User user, Integer userId) throws UserException;
    public List<User> searchUser(String query);
    public User findUserByJwt(String jwt);
}
