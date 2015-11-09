package net.nopainnocode.firstboard.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.nopainnocode.firstboard.domain.User;
import net.nopainnocode.firstboard.service.UserService;


@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired private UserService userService;
	
	// create new user
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewUser(@RequestBody @Valid User user, BindingResult bindingResult){
		
		if(bindingResult.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
		
		User newUser = userService.addNewUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	// read user's info
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> findUser(@PathVariable("userId") Long userId){
		
		User user = userService.findUser(userId);
		
		return ResponseEntity.ok(user);
	}
	
	// update user's info
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@RequestBody @Valid User enteredUser, BindingResult result){

		if(result.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
		
		User updatedUser = userService.updateUser(enteredUser);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUser);
	}
	
	// delete user
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE )
	public ResponseEntity<?> deleteUser( @PathVariable("userId") Long userId){

		if(userService.deleteUser(userId))
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}
}
