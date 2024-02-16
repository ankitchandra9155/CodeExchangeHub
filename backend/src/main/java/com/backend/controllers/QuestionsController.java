package com.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.AllQuestionResponseDto;
import com.backend.dtos.QuestionDto;
import com.backend.dtos.QuestionSearchResponseDto;
import com.backend.dtos.SingleQuestionResponseDto;
import com.backend.services.questions.QuestionService;

@RestController
@RequestMapping("/api")
public class QuestionsController {

	
	private final QuestionService questionService;

	public QuestionsController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}
	
	@PostMapping("/question")
	public ResponseEntity<?> postQuestion(@RequestBody QuestionDto questionDto){
		System.out.println(questionDto.toString());
		QuestionDto createdQuestionDto=questionService.addQuestion(questionDto);
		
		if(createdQuestionDto==null)
			return new ResponseEntity<>("Something went Wrong",HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestionDto);
	}
	
	@GetMapping("questions/{pageNumber}")
	public ResponseEntity<AllQuestionResponseDto> getAllQuestions(@PathVariable int pageNumber)
	{
		AllQuestionResponseDto allQuestionResponseDto=questionService.getAllQuestions(pageNumber);
		return ResponseEntity.ok(allQuestionResponseDto);
	}
	
	@GetMapping("/question/{questionId}/{userId}")
	public ResponseEntity<?> getQuestionById(@PathVariable Long questionId, @PathVariable Long userId){
		SingleQuestionResponseDto questionDto=questionService.getQuestionById(questionId,userId);
		if(questionDto==null)
			return new ResponseEntity<>("Question not found",HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(questionDto);
		
	}
	
	@GetMapping("questions/{userId}/{pageNumber}")
	public ResponseEntity<AllQuestionResponseDto> getAllQuestionsByUserId(@PathVariable Long userId ,@PathVariable int pageNumber)
	{
		AllQuestionResponseDto allQuestionResponseDto=questionService.getAllQuestionsByUserId(userId,pageNumber);
		return ResponseEntity.ok(allQuestionResponseDto);
	}
	
	@GetMapping("search/{title}/{pageNumber}")
	public ResponseEntity<?> searchQuestionByTitle(@PathVariable int pageNumber,@PathVariable String title)
	{
		QuestionSearchResponseDto questionSearchResponseDto=questionService.searchQuestionByTitle(title, pageNumber);
		if(questionSearchResponseDto==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(questionSearchResponseDto);
	}
	
}
