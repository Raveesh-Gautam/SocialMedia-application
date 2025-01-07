package com.social.services.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Chat;
import com.social.models.Message;
import com.social.models.User;
import com.social.repository.ChatRepository;
import com.social.repository.MessageRepository;
import com.social.services.ChatService;
import com.social.services.MassageService;

@Service
public class MessageServiceImpl implements MassageService{

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatService chatService;
	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {
		// TODO Auto-generated method stub
		Message message=new Message();
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		Chat chat=chatService.findChatById(chatId);
		message.setChat(chat);
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		Message savedMessage=messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception{
		// TODO Auto-generated method stub
		Chat chat=chatService.findChatById(chatId);
		return messageRepository.findByChatId(chatId);
	}

}
