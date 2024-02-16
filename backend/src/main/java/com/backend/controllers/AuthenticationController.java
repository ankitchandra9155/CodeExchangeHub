package com.backend.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.AuthenticationRequest;
import com.backend.dtos.AuthenticationResponse;
import com.backend.entities.User;
import com.backend.repositories.UserRepository;
import com.backend.utils.JwtUtil;



@RestController("/")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";
	
	@PostMapping("/authentication")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e)
		{
			throw new BadCredentialsException("Incorrect email or password");
		}catch(DisabledException e)
		{
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not created");
			return;
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		
		Optional<User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
		
		
		
		
		final String jwt=jwtUtil.GenerateToken(userDetails.getUsername());
		if(optionalUser.isPresent()) {
//			response.getWriter().write(new JSONObject()
//					.put("userId",optionalUser.get().getId())
//					.toString());
			PrintWriter writer=new PrintWriter(response.getOutputStream());
			JSONObject jsonResponse=new JSONObject().put("userId", optionalUser.get().getId())
					.put("email",optionalUser.get().getEmail() ).put("name", optionalUser.get().getName());
			writer.write(jsonResponse.toString());
//			writer.flush();
//			writer.close();
			response.addHeader("userId", optionalUser.get().getId().toString());
			response.addHeader("name", optionalUser.get().getName().toString());
			response.addHeader("email", optionalUser.get().getEmail().toString());
			response.addHeader("Access-Control-Expose-Headers", "Authorization, userId,name,email");
			response.setHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, X-Requested-With, Content-Type, Accept, X-Custom-header");
			response.setHeader(HEADER_STRING,TOKEN_PREFIX+jwt);
			
			
		}
		else
		response.sendError(HttpServletResponse.SC_NOT_FOUND,"User Not Found");
		
	}

}
