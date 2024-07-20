package com.sagar.social_media.service;

import com.sagar.social_media.models.Post;
import com.sagar.social_media.models.User;
import com.sagar.social_media.repository.PostRepository;
import com.sagar.social_media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{
@Autowired
    PostRepository postRepository;
@Autowired
UserService userService;

@Autowired
UserRepository userRepository;
    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        Post newPost=new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        Optional<User> user=userService.findUserById(userId);
        newPost.setUser(user.get());
        return postRepository.save(newPost);

    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
       Post post=findPostById(postId);
       Optional<User> user=userService.findUserById(userId);

       if (post.getUser().getId()!=user.get().getId()){
           throw  new Exception("you can't delete another users post");

       }
       postRepository.delete(post);
        return "post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
//        Post otp=postRepository.findById();

        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt=postRepository.findById(postId);
        if (opt.isEmpty()) throw new Exception("post not found with id "+ postId);

        return opt.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public User savedPost(Integer postId, Integer userId) throws Exception {

//        Post post=findPostById(postId);
        Optional<User> user=userService.findUserById(userId);
        if(user.get().getSavedPost().contains(postId)){
            user.get().getSavedPost().remove(postId);
        }
        else user.get().getSavedPost().add(postId);
        userRepository.save(user.get());
        return user.get();
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {

Post post=findPostById(postId);
//Optional<User> user=userService.findUserById(userId);
if (post.getLiked().contains(userId)){
    post.getLiked().remove(userId);
}
else post.getLiked().add(userId);
return postRepository.save(post);
    }

}
