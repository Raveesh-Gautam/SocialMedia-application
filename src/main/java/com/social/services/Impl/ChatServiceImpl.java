package com.social.services.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Chat;
import com.social.models.User;
import com.social.repository.ChatRepository;
import com.social.services.ChatService;

@Service
public class ChatServiceImpl  implements ChatService{

	@Autowired
	private ChatRepository chatRepository;
	@Override
	public Chat createChat(User reqUser, User user2) {
		// TODO Auto-generated method stub
		Chat isExist=chatRepository.findChatByUsersId(user2, reqUser);
		if(isExist!=null) {
			return isExist;
		}
		Chat chat=new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamps(LocalDateTime.now());
	
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Chat>opt=chatRepository.findById(chatId);
		
		if(opt.isEmpty()) {
			throw new Exception ("Chat not present with id -"+chatId);
		}
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		// TODO Auto-generated method stub
		return chatRepository.findChatByUserId(userId);

	}

	
	
}
