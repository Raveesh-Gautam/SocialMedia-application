package com.social.services.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Story;
import com.social.models.User;
import com.social.repository.StoryRepository;
import com.social.services.StoryService;
import com.social.services.UserService;

@Service
public class StoryServiceImpl  implements StoryService{
	@Autowired
	private StoryRepository storyRepository;

	@Autowired
	private UserService userService;
	@Override
	public Story createStory(Story story, Integer userId) throws Exception {
		// TODO Auto-generated method stub
	User user=	userService.findUserById(userId);
	Story newStory=new Story();
	newStory.setCaptions(story.getCaptions());
	newStory.setImage(story.getImage());
	newStory.setTimestamps(LocalDateTime.now());
	newStory.setUser(user);
		return storyRepository.save(newStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception{
		// TODO Auto-generated method stub
		User  user=userService.findUserById(userId);
		return storyRepository.findByUserId(userId);
	}

}
