package com.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.AnswerDto;
import com.backend.services.answer.AnswerService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@PostMapping
	public ResponseEntity<?> addAnswer(@RequestBody AnswerDto answerDto){
		AnswerDto createdAnswerDto=answerService.postAnswer(answerDto);
		if(createdAnswerDto==null)
		{
			return new ResponseEntity<>("Something Went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswerDto);
	}

}
