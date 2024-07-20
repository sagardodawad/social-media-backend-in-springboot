package com.sagar.social_media.controller;

import com.sagar.social_media.models.Story;
import com.sagar.social_media.models.User;
import com.sagar.social_media.service.StoryService;
import com.sagar.social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @PostMapping("/createStory")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt)
    {
        User reqUser= userService.findUserByJwt(jwt);
        Story createdStory=storyService.createStory(story,reqUser);
    return createdStory;
    }
    @GetMapping("/story/user/{userId}")
    public List<Story> findUserStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
//        User reqUser= userService.findUserByJwt(jwt);
        List<Story> stories=storyService.findStoryByUserId(userId);
    return stories;
    }

}
