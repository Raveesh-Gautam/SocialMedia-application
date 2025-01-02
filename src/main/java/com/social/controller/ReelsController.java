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

import com.social.models.Reels;
import com.social.models.User;
import com.social.repository.ReelsRepository;
import com.social.services.ReelsService;
import com.social.services.UserService;

@RestController
@RequestMapping("/api/reels")
public class ReelsController {
@Autowired
private ReelsService reelsService;
@Autowired
private UserService userService;
@PostMapping("/")
	public Reels createReels(@RequestBody Reels reel,@RequestHeader("Authorization") String  jwt) {
	
		User reqUser=userService.findUserProfileByJwt(jwt);
		
	Reels createdReels=	reelsService.createReel(reel, reqUser);
		return createdReels;
		
	}
@GetMapping("/")
public List<Reels> findAllReels() {

	return reelsService.findAllReels();
	
}
@GetMapping("/{userId}")
public List<Reels> findAllReels(@PathVariable("userId") int userId) throws Exception {

	return reelsService.findUsersReel(userId);
	
}


}
