package com.social.services.Impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Comment;
import com.social.models.Post;
import com.social.models.User;
import com.social.repository.CommentRepository;
import com.social.repository.PostRepository;
import com.social.services.CommentService;
import com.social.services.PostService;
import com.social.services.UserService;

@Service
public class CommentServiceImpl implements CommentService {
@Autowired
	private PostService postService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostRepository postRepository;
	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
		Post post =postService.findPostById(postId);
		comment.setUser(user);
		System.out.println(comment.getContent()+"comment ->>>>>>>");
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		Comment com=commentRepository.save(comment);
		
		post.getComments().add(com);
		postRepository.save(post);
	
		return com;
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		User user=userService.findUserById(userId);
		Comment comment =findCommentById(commentId);
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}else
			comment.getLiked().remove(user);
		return commentRepository.save(comment);
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Comment> opt=commentRepository.findById(commentId);
		if(opt.isEmpty()) {
			throw new Exception("Comment not exist");
		}
		return opt.get();
	}

}
