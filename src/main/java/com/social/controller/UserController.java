package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.exception.UserException;
import com.social.models.User;
import com.social.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<String> createUser(@RequestBody User user) throws Exception{
	try {
		User newUser=	userService.registerUser(user);
		return new ResponseEntity<>("User registered",HttpStatus.CREATED);
	}catch(UserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

	} catch(Exception ex) {
        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);

	}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int userId) {
		
		User user=userService.findUserById(userId);
		try {
			return new ResponseEntity<>(user,HttpStatus.OK);
	} catch(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);	}
	
	
	}
	
	@PutMapping("/follow/users/{userId1}/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization")String jwt,@PathVariable("userId2") int userId2) {
	User reqUser=	userService.findUserProfileByJwt(jwt);
		return userService.followUser(reqUser.getId(), userId2);
	}
	
	@GetMapping("/search")
	public List<User> searchUser(@RequestParam("query") String query){
		return userService.searchUser(query);
	}
	
	
	@GetMapping("/getAll")
	public List<User> findAllUser(){
		return userService.getAllUser();
	}
	
	
	@GetMapping("/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
		
		
	User user= userService.findUserProfileByJwt(jwt);
	user.setPassword(null);
	return user;
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestHeader("Authorization")String jwt,@RequestBody User user) {
		User reqUser=userService.findUserProfileByJwt(jwt);
		User updatedUser=userService.updateUser(user, reqUser.getId());
		
		return updatedUser;
		
	}
}

