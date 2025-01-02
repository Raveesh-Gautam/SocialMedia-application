package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Comment;
import com.social.models.User;
import com.social.services.CommentService;
import com.social.services.UserService;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/create/{postId}")
	public Comment createComment(@RequestBody Comment comment,@RequestHeader("Authorization") String jwt,@PathVariable("postId") Integer postId) throws Exception {
	System.out.println(comment.getContent()+"controller->>>>>>>>");
	System.out.println(postId+"controller->>>>>>>>");
    System.out.println("Received Comment in Controller: " + comment);  // Print the entire comment object


		User user=	userService.findUserProfileByJwt(jwt);
	Comment createdCom=commentService.createComment(comment, postId, user.getId());
		return createdCom;
	}
	
	@PutMapping("/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt,@PathVariable("commentId") Integer commentId) throws Exception {
	User user=	userService.findUserProfileByJwt(jwt);
	Comment likeCom=commentService.likeComment(commentId, user.getId());
		return likeCom;
	}

}
