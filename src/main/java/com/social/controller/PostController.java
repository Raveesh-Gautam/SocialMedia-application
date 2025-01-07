package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.exception.UserException;
import com.social.models.Post;
import com.social.models.User;
import com.social.response.ApiResponse;
import com.social.services.PostService;
import com.social.services.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
private	PostService postService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createPost(@RequestHeader("Authorization")String jwt,@RequestBody Post post) throws UserException{
		User reqUser=userService.findUserProfileByJwt(jwt);
		postService.createPost(post, reqUser.getId());
		
		return new ResponseEntity<>("Post created",HttpStatus.CREATED);
	}
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization")String jwt,@PathVariable("postId") Integer postId) throws Exception{
		User reqUser=userService.findUserProfileByJwt(jwt);

		String message=postService.deletePost(postId, reqUser.getId());
		ApiResponse resp=new ApiResponse(message,true);
		return new ResponseEntity<>(resp,HttpStatus.OK);
	
	}

	@GetMapping("/{postId}")
	public ResponseEntity<Post> findPostById(@PathVariable("postId") Integer postId) throws Exception{
			Post post=postService.findPostById(postId);
		
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable("userId") Integer userId) throws UserException{
		
		List<Post> posts=postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Post>> findAllPost() throws Exception{
		
		List<Post> posts=postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@PutMapping("/{postId}/user")
	public ResponseEntity<Post> savedPostHandler(@PathVariable("postId") Integer postId,@RequestHeader("Authorization")String jwt) throws Exception{
		User reqUser=userService.findUserProfileByJwt(jwt);

		Post post=postService.savedPost(postId, reqUser.getId());
		
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	@PutMapping("/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable("postId") Integer postId,@RequestHeader("Authorization")String jwt) throws Exception{
		User reqUser=userService.findUserProfileByJwt(jwt);

		Post post=postService.likePost(postId, reqUser.getId());
		
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	
	
	
	
}