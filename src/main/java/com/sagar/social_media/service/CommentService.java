package com.sagar.social_media.service;

import com.sagar.social_media.models.Comment;

public interface CommentService {

    public Comment createComment(Comment comment, Integer postId,Integer userId) throws Exception;
    public Comment likeComment(Integer CommentId, Integer userId) throws Exception;
    public Comment findCommentById(Integer commentId) throws Exception;

}
