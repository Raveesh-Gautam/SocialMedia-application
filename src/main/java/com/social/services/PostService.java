package com.social.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.social.exception.UserException;
import com.social.models.Post;
@Service
public interface PostService {
public Post createPost(Post post,Integer userId) throws UserException;
	
public String deletePost(Integer postId, Integer userId) throws Exception;

public List<Post> findPostByUserId (Integer userId) throws UserException;

public Post findPostById(Integer postId) throws Exception;


 public List<Post> findAllPost() throws Exception;

public Post savedPost(Integer postId, Integer userId) throws Exception, UserException;

public Post likePost (Integer postId, Integer userId) throws UserException, Exception;
}
