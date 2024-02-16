package com.backend.services.questions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import com.backend.dtos.AllQuestionResponseDto;
import com.backend.dtos.AnswerDto;
import com.backend.dtos.QuestionDto;
import com.backend.dtos.QuestionSearchResponseDto;
import com.backend.dtos.SingleQuestionResponseDto;
import com.backend.entities.Answers;
import com.backend.entities.QuestionVote;
import com.backend.entities.Questions;
import com.backend.entities.User;
import com.backend.enums.VoteType;
import com.backend.repositories.AnswerRepository;
import com.backend.repositories.QuestionRepository;
import com.backend.repositories.UserRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	public static final int SEARCH_RESULT_PER_PAGE=5;
	@Override
	public QuestionDto addQuestion(QuestionDto questionDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(questionDto.getUserId());
		System.out.println("Question Service "+optionalUser.toString());
		if(optionalUser.isPresent()) {
			User user=optionalUser.get();
			Questions question=new Questions();
			question.setTitle(questionDto.getTitle());
			question.setBody(questionDto.getBody());
			question.setTags(questionDto.getTags());
			question.setCreatedDate(new Date());
			question.setUser(user);
			Questions createdQuestion=questionRepository.save(question);
			
			QuestionDto createdQuestionDto=new QuestionDto();
			createdQuestionDto.setId(createdQuestion.getId());
			createdQuestionDto.setTitle(createdQuestion.getTitle());
			createdQuestionDto.setBody(createdQuestion.getBody());
			createdQuestionDto.setCreatedDate(createdQuestion.getCreatedDate());
			return createdQuestionDto;
			
			
		}
		System.out.println("Failed!!!");
		return null;
	}
	@Override
	public AllQuestionResponseDto getAllQuestions(int pageNumber) {
		// TODO Auto-generated method stub
		Pageable paging=PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
		Page<Questions> questionPage=questionRepository.findAll(paging);
		
		//Conerting and storing Page type list to QuestionDTO which is done in the questions class
		AllQuestionResponseDto allQuestionResponseDto=new AllQuestionResponseDto();
		allQuestionResponseDto.setQuestionDtoList(questionPage.getContent().stream()
				.map(Questions::getQuestionDto).collect(Collectors.toList()));
		
		allQuestionResponseDto.setPageNumber(questionPage.getPageable().getPageNumber());
		allQuestionResponseDto.setTotalPages(questionPage.getTotalPages());
		return allQuestionResponseDto;
	}
	@Override
	public SingleQuestionResponseDto getQuestionById(Long questionId,Long userId) {
		// TODO Auto-generated method stub
		Optional<Questions> optionalQuestion=questionRepository.findById(questionId);
		if(optionalQuestion.isPresent()) {
//			Questions question=optionalQuestion.get();
//			QuestionDto responseQuestion=question.getQuestionDto();
			
			Questions existingQuestion=optionalQuestion.get();
			Optional<QuestionVote> optionalQuestionVote=existingQuestion.getQuestionVoteList()
					.stream().filter(vote->vote.getUser().getId().equals(userId)).findFirst();
			QuestionDto questionDto=optionalQuestion.get().getQuestionDto();
			
			if(optionalQuestionVote.isPresent()) {
				if(optionalQuestionVote.get().getVoteType().equals(VoteType.UPVOTE))
					questionDto.setVoted(1);
				else
					questionDto.setVoted(-1);
			}
			else
				questionDto.setVoted(0);
			
			SingleQuestionResponseDto singleQuestionResponseDto=new SingleQuestionResponseDto();
			singleQuestionResponseDto.setQuestionDto(questionDto);
			
			List<Answers> answerList=answerRepository.findAllByQuestionId(questionId);
			List<AnswerDto> answerDtoList=new ArrayList<>();
			
			for(Answers answer:answerList) {
				AnswerDto answerDto=answer.getAnswerDto();
				answerDtoList.add(answerDto);
			}
			singleQuestionResponseDto.setAnswerDtoList(answerDtoList);
			
			return singleQuestionResponseDto;
		}
		
		return null;
	}
	@Override
	public AllQuestionResponseDto getAllQuestionsByUserId(Long userId,int pageNumber) {
		// TODO Auto-generated method stub
		Pageable paging=PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
		Page<Questions> questionPage=questionRepository.findAllByUserId(userId,paging);
		
		//Conerting and storing Page type list to QuestionDTO which is done in the questions class
		AllQuestionResponseDto allQuestionResponseDto=new AllQuestionResponseDto();
		allQuestionResponseDto.setQuestionDtoList(questionPage.getContent().stream()
				.map(Questions::getQuestionDto).collect(Collectors.toList()));
		
		allQuestionResponseDto.setPageNumber(questionPage.getPageable().getPageNumber());
		allQuestionResponseDto.setTotalPages(questionPage.getTotalPages());
		return allQuestionResponseDto;
	}
	
	
	
	@Override
	public QuestionSearchResponseDto searchQuestionByTitle(String title, int pageNum) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(pageNum, SEARCH_RESULT_PER_PAGE);
		Page<Questions> questionPage;
		if(title==null ||title.equals("null"))
			questionPage=questionRepository.findAll(pageable);
		else 
			questionPage=questionRepository.findAllByTitleContaining(title,pageable);
		
		QuestionSearchResponseDto questionSearchResponseDto=new QuestionSearchResponseDto();
		questionSearchResponseDto.setQuestionDtoList(questionPage.stream()
					.map(Questions::getQuestionDto).collect(Collectors.toList()));
		questionSearchResponseDto.setPageNumber(questionPage.getPageable().getPageNumber());
		questionSearchResponseDto.setTotalPages(questionPage.getTotalPages());
		return questionSearchResponseDto;
		
	}

}
