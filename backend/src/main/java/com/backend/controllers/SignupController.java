package com.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.SignupDTO;
import com.backend.dtos.UserDTO;
import com.backend.services.user.UserService;

@RestController
@RequestMapping
public class SignupController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping({"/signup"})
	public ResponseEntity<?> createUser(@RequestBody SignupDTO signupDTO)
	{
		System.out.println(signupDTO.toString());
		if(userService.hasUserWithEmail(signupDTO.getEmail()))
			return new ResponseEntity<>("user already exist!",HttpStatus.NOT_ACCEPTABLE);
		UserDTO createdUser= userService.createUser(signupDTO);
		if(createdUser==null) {
			return new ResponseEntity<>("User not created!",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
		
	}

}
