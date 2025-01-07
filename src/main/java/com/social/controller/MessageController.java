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

import com.social.models.Message;
import com.social.models.User;
import com.social.services.MassageService;
import com.social.services.UserService;

@RestController
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	
private MassageService messageService;	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/chat/{chatId}")
	public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt, @PathVariable("chatId") Integer chatId) throws Exception {
	User user=	userService.findUserProfileByJwt(jwt);
	Message message=messageService.createMessage(user, chatId, req);
		return message;
	}
	
	@GetMapping("/chat/{chatId}")
	public List<Message> findChatsMessage(@PathVariable("chatId") Integer chatId,@RequestHeader("Authorization") String jwt) throws Exception{
		User user=	userService.findUserProfileByJwt(jwt);
List<Message> messages=messageService.findChatsMessages(chatId);
		return messages;
	}
}
