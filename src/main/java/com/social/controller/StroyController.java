package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Story;
import com.social.models.User;
import com.social.services.StoryService;
import com.social.services.UserService;

@RestController
@RequestMapping("/api/story")
public class StroyController {

	@Autowired
	private StoryService storyService;
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/")
	public  Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) throws Exception {
		User user=userService.findUserProfileByJwt(jwt);
		
		Story created= storyService.createStory(story, user.getId());
		return created;
	}
	@GetMapping("/user/{userId}")
	public  List<Story> findUserStory( @PathVariable("userId") int userId,@RequestHeader("Authorization") String jwt) throws Exception {
		User requser=userService.findUserProfileByJwt(jwt);
		
		
	List<Story> stories=storyService.findStoryByUserId(userId);
		return stories;
	}
	
	
	
}
