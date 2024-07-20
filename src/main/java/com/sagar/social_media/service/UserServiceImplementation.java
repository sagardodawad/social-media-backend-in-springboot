package com.sagar.social_media.service;

import com.sagar.social_media.config.JwtProvider;
import com.sagar.social_media.exceptions.UserException;
import com.sagar.social_media.models.User;
import com.sagar.social_media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserServiceImplementation implements UserService {
@Autowired
    UserRepository userRepository;
    @Override
    public User registerUser(User user) {
        User newUser=new User();
        newUser.setId(user.getId());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setGender(user.getGender());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findUserById(Integer userId) throws UserException {
        Optional<User> user=userRepository.findById(userId);
        if (user.isPresent()) return user;
        throw new UserException("user does not exist with id "+userId);

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws UserException {
        Optional<User> optionalUser1 = findUserById(userId1);
        Optional<User> optionalUser2 = findUserById(userId2);

        if (optionalUser1.isPresent() && optionalUser2.isPresent()) {
            User user1 = optionalUser1.get();
            User user2 = optionalUser2.get();

            if (!user1.getFollowings().contains(userId2)) {
                user1.getFollowings().add(userId2);
                user2.getFollowers().add(userId1);
                userRepository.save(user1);
                userRepository.save(user2);
            }

            return user1;
        } else {
            throw new UserException("One or both users do not exist");
        }
    }

    @Override
    public User updateUser(User user,Integer id) throws UserException {
        Optional<User> user1=userRepository.findById(id);
        if (user1.isPresent()) {
            User oldUser = user1.get();
            if (user.getEmail() != null) {
                oldUser.setEmail(user.getEmail());
            }
            if (user.getFirstName() != null) oldUser.setFirstName(user.getFirstName());
            if (user.getLastName() != null) oldUser.setLastName(user.getLastName());
            if (user.getPassword() != null) oldUser.setPassword(user.getPassword());
            if (user.getGender() != null) oldUser.setGender(user.getGender());
            return userRepository.save(oldUser);
        }
        throw new UserException("user not present with id "+id);
    }

    @Override
    public List<User> searchUser(String query) {

        return userRepository.searchUser(query);
    }
    public User findUserByJwt(String jwt){
        String email= JwtProvider.getEmailFromJwtToken(jwt);
        User user=userRepository.findByEmail(email);
        return user;
    }
}

