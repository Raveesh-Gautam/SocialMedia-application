package com.social.services.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.exception.UserException;
import com.social.models.Post;
import com.social.models.User;
import com.social.repository.PostRepository;
import com.social.repository.UserRepository;
import com.social.services.PostService;
import com.social.services.UserService;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Post createPost(Post post, Integer userId) throws UserException {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
	 	Post newPost=new Post();
	 	newPost.setUser(user);
	 	newPost.setCreatedAt(LocalDateTime.now());
	 	newPost.setImage(post.getImage());
	 	newPost.setCaption(post.getCaption());
	 	
		return postRepository.save(newPost);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
		
         Post post=findPostById(postId);
         if(post.getUser().getId()!=user.getId()) {
        	 throw new Exception("You can't delete another user post");
         }
         postRepository.delete(post);
        
		return "post deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws UserException {
		// TODO Auto-generated method stub
		
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		// TODO Auto-generated method stub
	Optional<Post> post=	postRepository.findById(postId);
	if(!post.isPresent()) {
		throw new  Exception("post not found with id"+postId);
	}
		return post.get();
	}

	@Override
	public List<Post> findAllPost() throws Exception {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception, UserException {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
		
        Post post=findPostById(postId);
        if(user.getSavedPost().contains(post)) {
        	user.getSavedPost().remove(post);
        }else {
        	user.getSavedPost().add(post);
        }
        
		 userRepository.save(user) ;
		 return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws UserException, Exception {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
		
        Post post=findPostById(postId);
        if(post.getLikes().contains(user)) {
        	post.getLikes().remove(user);
        }else {
        	post.getLikes().add(user);
        }
//        post.getLikes().add(user);
		return postRepository.save(post);
	}

}
