package com.backend.services.user;

import com.backend.dtos.SignupDTO;
import com.backend.dtos.UserDTO;

public interface UserService {

	UserDTO createUser(SignupDTO signupDTO);

	boolean hasUserWithEmail(String email);

}
