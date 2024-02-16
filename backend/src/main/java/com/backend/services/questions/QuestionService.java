package com.backend.services.questions;

import com.backend.dtos.AllQuestionResponseDto;
import com.backend.dtos.QuestionDto;
import com.backend.dtos.QuestionSearchResponseDto;
import com.backend.dtos.SingleQuestionResponseDto;

public interface QuestionService {

	QuestionDto addQuestion(QuestionDto questionDto);

	AllQuestionResponseDto getAllQuestions(int pageNumber);

	SingleQuestionResponseDto getQuestionById(Long questionId,Long userId);

	AllQuestionResponseDto getAllQuestionsByUserId(Long userId,int pageNumber);
	
	QuestionSearchResponseDto searchQuestionByTitle(String title,int pageNum);

}
