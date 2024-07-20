package com.sagar.social_media.service;

import com.sagar.social_media.models.Story;
import com.sagar.social_media.models.User;
import com.sagar.social_media.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImplementation implements StoryService{
  @Autowired
    private StoryRepository storyRepository;
@Autowired
private UserService userService;
    @Override
    public Story createStory(Story story, User user) {
       Story createdStory=new Story();
       createdStory.setCaptions(story.getCaptions());
       createdStory.setImage(story.getImage());
       createdStory.setUser(user);
       createdStory.setTimeStamp(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
      Optional<User> user=userService.findUserById(userId);
      if (user.isEmpty()) throw new Exception("user not found");
        return storyRepository.findByUserId(userId);
    }
}
