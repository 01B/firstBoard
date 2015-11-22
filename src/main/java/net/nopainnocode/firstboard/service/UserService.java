package net.nopainnocode.firstboard.service;

import net.nopainnocode.firstboard.domain.User;

public interface UserService {

	User addNewUser(User user);
	
	User findUser(long userId);

	User findUser(String username);
	
	User updateUser(User user);
	
	boolean deleteUser(Long userId);

	User login(String username, String password);
}
