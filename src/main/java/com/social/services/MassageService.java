package com.social.services;

import java.util.List;

import com.social.models.Chat;
import com.social.models.Message;
import com.social.models.User;

public interface MassageService {

	public List<Message> findChatsMessages(Integer chatId) throws Exception ;
	public Message createMessage(User user, Integer chatId, Message req)throws Exception;
}
