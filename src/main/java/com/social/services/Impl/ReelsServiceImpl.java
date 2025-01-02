package com.social.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Reels;
import com.social.models.User;
import com.social.repository.ReelsRepository;
import com.social.services.ReelsService;
import com.social.services.UserService;
@Service
public class ReelsServiceImpl implements ReelsService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReelsRepository reelsRepository;
	
	@Override
	public Reels createReel(Reels reel, User user) {
		// TODO Auto-generated method stub
		Reels createReal=new Reels();
		createReal.setTitle(reel.getTitle());
		createReal.setUser(user);
		createReal.setVideo(reel.getVideo());
		return reelsRepository.save(createReal);
	}

	@Override
	public List<Reels> findAllReels() {
		// TODO Auto-generated method stub
		return reelsRepository.findAll();
	
	}

	@Override
	public List<Reels> findUsersReel(Integer userId) throws Exception{
		// TODO Auto-generated method stub
		userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
