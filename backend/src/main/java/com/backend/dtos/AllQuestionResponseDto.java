package com.backend.dtos;

import java.util.List;

import lombok.Data;

@Data
public class AllQuestionResponseDto {

	private List<QuestionDto> questionDtoList;
	
	private Integer totalPages;
	
	private Integer pageNumber;
	
	

	public AllQuestionResponseDto() {
		super();
	}

	public AllQuestionResponseDto(List<QuestionDto> questionDtoList, Integer totalPages, Integer pageNumber) {
		super();
		this.questionDtoList = questionDtoList;
		this.totalPages = totalPages;
		this.pageNumber = pageNumber;
	}

	public List<QuestionDto> getQuestionDtoList() {
		return questionDtoList;
	}

	public void setQuestionDtoList(List<QuestionDto> questionDtoList) {
		this.questionDtoList = questionDtoList;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
}
