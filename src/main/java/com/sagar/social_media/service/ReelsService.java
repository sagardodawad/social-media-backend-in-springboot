package com.sagar.social_media.service;

import com.sagar.social_media.models.Reels;
import com.sagar.social_media.models.User;

import java.util.List;

public interface ReelsService {
    public Reels createReel(Reels reel, User user);
    public List<Reels> findAllReels();
    public List<Reels> findUserReel(Integer userId) throws Exception;

}
