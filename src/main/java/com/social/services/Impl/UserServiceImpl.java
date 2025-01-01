package com.social.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.config.JwtProvider;
import com.social.exception.UserException;
import com.social.models.User;
import com.social.repository.UserRepository;
import com.social.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public User registerUser(User user) throws Exception {
		// TODO Auto-generated method stub
		User existingUser=userRepository.findByEmail(user.getEmail());
		
		if(existingUser!=null) {
	        throw new Exception("Email is already registered"); 
		}
		
		return userRepository.save(user);
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> user=userRepository.findById(userId);
		if(!user.isPresent()) {
			throw new UserException("User not found found with id:"+userId);
		}
		return user.get();
	}

	@Override
	public User findUserByEmail(String email) throws UserException {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email);
		if(user==null) {
			throw new UserException("User not found found with email:"+email);
		}
		return user;
		
	}

	@Override
	public User followUser(Integer reqUserId, Integer followUserId) throws UserException {
		// TODO Auto-generated method stub
		User user1=findUserById(reqUserId);
		User user2=findUserById(followUserId);
		user2.getFollowers().add(user1.getId());
		user1.getFollowings().add(user2.getId());
		userRepository.save(user1);
		userRepository.save(user2);
		return user1;
	}

	@Override
	public List<User> searchUser(String query) throws UserException {
		// TODO Auto-generated method stub
		return userRepository.searchUser(query);
	}

	@Override
	public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		// TODO Auto-generated method stub
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		User user=userRepository.findByEmail(email);
		return user;
	}

	@Override
	public void updatePassword(User user, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPasswordResetEmail(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	
	}

	@Override
	public User updateUser(User user, Integer reqid) {
		// TODO Auto-generated method stub
		User newUser=findUserById(reqid);
		 if (user.getFirstName() != null) {
		        newUser.setFirstName(user.getFirstName());
		    }
		    if (user.getLastName() != null) {
		        newUser.setLastName(user.getLastName());
		    }
		    if (user.getGender() != null) {
		        newUser.setGender(user.getGender());
		    }
User update=	userRepository.save(newUser);
return update;
		
	}

}
