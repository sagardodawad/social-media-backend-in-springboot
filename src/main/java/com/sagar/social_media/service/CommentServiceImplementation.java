package com.sagar.social_media.service;

import com.sagar.social_media.models.Comment;
import com.sagar.social_media.models.Post;
import com.sagar.social_media.models.User;
import com.sagar.social_media.repository.CommentRepository;
import com.sagar.social_media.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements  CommentService{
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        Optional<User> user=userService.findUserById(userId);
        Post post=postService.findPostById(postId);
        comment.setUser(user.get());
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment=commentRepository.save(comment);
  post.getComments().add(savedComment);
  postRepository.save(post);
        return savedComment;
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        Comment comment=findCommentById(commentId);
        Optional<User> user=userService.findUserById(userId);
   if (!comment.getLiked().contains(user.get())){
       comment.getLiked().add(user.get());
   }
   else comment.getLiked().remove(user.get());
   return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
       Optional<Comment> opt=commentRepository.findById(commentId);
       if (opt.isEmpty()) throw new Exception("comment not exist");
       return opt.get();
    }
}
