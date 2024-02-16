package com.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.QuestionVoteDto;
import com.backend.services.vote.VoteService;

@RestController
@RequestMapping("/api")
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	@PostMapping("/vote")
	public ResponseEntity<?> addVoteToQuestion(@RequestBody QuestionVoteDto questionVoteDto )
	{
		QuestionVoteDto votedQuestion=voteService.addVoteToQuestion(questionVoteDto);
		if(votedQuestion==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went Wrong!!");
		return ResponseEntity.status(HttpStatus.CREATED).body(votedQuestion);
	}

}
