package com.backend.dtos;

import java.util.List;

public class SingleQuestionResponseDto {
	
	private QuestionDto questionDto;
	
	List<AnswerDto> answerDtoList;

	
	public SingleQuestionResponseDto() {
		super();
	}

	public SingleQuestionResponseDto(QuestionDto questionDto, List<AnswerDto> answerDtoList) {
		super();
		this.questionDto = questionDto;
		this.answerDtoList = answerDtoList;
	}

	public QuestionDto getQuestionDto() {
		return questionDto;
	}

	public void setQuestionDto(QuestionDto questionDto) {
		this.questionDto = questionDto;
	}

	public List<AnswerDto> getAnswerDtoList() {
		return answerDtoList;
	}

	public void setAnswerDtoList(List<AnswerDto> answerDtoList) {
		this.answerDtoList = answerDtoList;
	}

}
