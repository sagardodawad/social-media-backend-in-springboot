package com.sagar.social_media.service;

import com.sagar.social_media.models.Story;
import com.sagar.social_media.models.User;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story, User user);
    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
