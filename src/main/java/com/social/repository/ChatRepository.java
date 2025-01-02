package com.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.social.models.Chat;
import com.social.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	@Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id = :userId")
	public List<Chat> findChatByUserId(Integer userId);
	
	@Query("Select c from Chat c Where :user Member of c.users And :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user") User user,@Param("reqUser") User reqUser);
}
