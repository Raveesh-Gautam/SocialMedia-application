package com.social.services;

import java.util.List;

import com.social.models.Story;

public interface StoryService {

	public Story createStory(Story story, Integer userId) throws Exception;

	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
