package com.backend.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.dtos.SignupDTO;
import com.backend.dtos.UserDTO;
import com.backend.entities.User;
import com.backend.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO createUser(SignupDTO signupDTO) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setEmail(signupDTO.getEmail());
		user.setName(signupDTO.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
		
		User createdUser=userRepository.save(user);
		UserDTO createdUserDTO=new UserDTO();
		createdUserDTO.setId(createdUser.getId());
		createdUserDTO.setName(createdUser.getName());
		createdUserDTO.setEmail(createdUser.getEmail());
		
		return createdUserDTO;
		
	}

	@Override
	public boolean hasUserWithEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findFirstByEmail(email).isPresent();
	}

}
