package com.sagar.social_media.controller;

import com.sagar.social_media.models.Reels;
import com.sagar.social_media.models.User;
import com.sagar.social_media.repository.ReelsRepository;
import com.sagar.social_media.service.ReelsService;
import com.sagar.social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReelsController {
    @Autowired
    private ReelsService reelsService;
    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;
    @PostMapping("/createReels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){
        User reqUser=userService.findUserByJwt(jwt);
        Reels createdReel=reelsService.createReel(reel,reqUser);
        return reelsRepository.save(createdReel);
    }
    @GetMapping("/allReels")
    public List<Reels> findAllReels(){
       return reelsService.findAllReels();
    }
    @GetMapping("/userReels/{userId}")
    public List<Reels> findAllUserReels(@PathVariable Integer userId) throws Exception {
       return reelsService.findUserReel(userId);
    }



}
