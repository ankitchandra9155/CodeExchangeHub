package com.backend.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.entities.User;
import com.backend.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository UserRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOptional=UserRepository.findFirstByEmail(email);
		if(!userOptional.isPresent())
			throw new UsernameNotFoundException("User name doesn't exist");
		return new org.springframework.security.core.userdetails.User(userOptional.get().getEmail(),userOptional.get().getPassword(),new ArrayList<>());
	}

}
