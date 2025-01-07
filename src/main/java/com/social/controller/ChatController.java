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

import com.social.exception.UserException;
import com.social.models.Chat;
import com.social.models.User;
import com.social.request.CreateChatRequest;
import com.social.services.ChatService;
import com.social.services.UserService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
@Autowired
private ChatService chatService;
@Autowired
private UserService userService;
@PostMapping("/")
public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest req) throws UserException {
	User user1 =userService.findUserProfileByJwt(jwt);
User user2=userService.findUserById(req.getUser2Id());
	Chat chat=chatService.createChat(user1, user2);
	
	return chat;
}
@GetMapping("/")
public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) throws UserException {
	User user =userService.findUserProfileByJwt(jwt);
return	chatService.findUsersChat(user.getId());
	
}


}
