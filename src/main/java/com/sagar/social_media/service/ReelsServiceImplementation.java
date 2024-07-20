package com.sagar.social_media.service;

import com.sagar.social_media.models.Reels;
import com.sagar.social_media.models.User;
import com.sagar.social_media.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReelsServiceImplementation implements ReelsService{

    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;
    @Override
    public Reels createReel(Reels reel, User user) {
      Reels createReel=new Reels();
      createReel.setTitle(reel.getTitle());
      createReel.setUser(user);
      createReel.setVideo(reel.getVideo());
      createReel.setCreatedAt(LocalDateTime.now());
        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReel(Integer userId) throws Exception {
        Optional<User> user=userService.findUserById(userId);
        if (user.isPresent()) return reelsRepository.findByUserId(userId);
        throw new Exception("user not found");
    }
}
