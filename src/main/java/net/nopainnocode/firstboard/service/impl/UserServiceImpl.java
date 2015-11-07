package net.nopainnocode.firstboard.service.impl;

import net.nopainnocode.firstboard.support.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import net.nopainnocode.firstboard.domain.User;
import net.nopainnocode.firstboard.repository.UserRepository;
import net.nopainnocode.firstboard.service.UserService;
import net.nopainnocode.firstboard.support.error.NicknameDuplicatedException;
import net.nopainnocode.firstboard.support.error.UsernameDuplicatedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {


	@Autowired UserRepository userRepository;

	@Override
	public User addNewUser(User newUser) {
		
		userValidationCheck(newUser);
		return userRepository.save(newUser);
	}


	@Override
	public User findUser(long userId) {

		return findUserIfExist(userId);
	}


	@Override
	public User findUser(String username) {

		User user = userRepository.findByUsername(username);

		// if user does not exist
		if(null == user)
			throw new UserNotFoundException(username);

		return user;
	}

	@Override
	public User updateUser(User enteredUser) {

		// todo : if modified nickname is duplicated

		return findUserIfExist(enteredUser.getUserId()).updateUser(enteredUser);
	}


	@Override
	public boolean deleteUser(Long userId) {

		if(null == findUserIfExist(userId))
			return false;
		else {
			userRepository.delete(userId);

			return true;
		}
	}

	private void userValidationCheck(User user) {

		// is username exist?
		if(isUsernameDuplicated(user.getUsername()))
			throw new UsernameDuplicatedException(user.getUsername());

		// is nickname exist?
		if(isNicknameDuplicated(user.getNickname()))
			throw new NicknameDuplicatedException(user.getNickname());
	}

	private boolean isUsernameDuplicated(String username) {

		return null != userRepository.findByUsername(username);
	}

	private boolean isNicknameDuplicated(String nickname) {

		return null != userRepository.findByNickname(nickname);
	}

	private User findUserIfExist(long userId) {

		User user = userRepository.findOne(userId);

		// if user does not exist
		if(null == user)
			throw new UserNotFoundException(userId);

		return user;
	}
}
