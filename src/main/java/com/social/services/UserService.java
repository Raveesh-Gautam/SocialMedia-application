package com.social.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.social.exception.UserException;
import com.social.models.User;

@Service
public interface UserService {

public User registerUser(User user) throws Exception;
public User findUserById(Integer userId) throws UserException;
public User findUserByEmail(String email) throws UserException;
public User followUser(Integer reqUserId,Integer followUserId) throws UserException;
public List<User> searchUser(String query) throws UserException;
public User updateUserDetails(User updatedUser,User existingUser) throws UserException;
public User findUserProfileByJwt(String jwt) throws UserException;
void updatePassword(User user, String newPassword);
void sendPasswordResetEmail(User user);
public List<User> getAllUser();
public User updateUser(User user,Integer reqid);

}
