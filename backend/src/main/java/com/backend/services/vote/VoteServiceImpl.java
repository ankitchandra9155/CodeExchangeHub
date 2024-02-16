package com.backend.services.vote;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dtos.QuestionVoteDto;
import com.backend.entities.QuestionVote;
import com.backend.entities.Questions;
import com.backend.entities.User;
import com.backend.enums.VoteType;
import com.backend.repositories.QuestionRepository;
import com.backend.repositories.QuestionVoteRepository;
import com.backend.repositories.UserRepository;

@Service
public class VoteServiceImpl implements VoteService{

	@Autowired
	private QuestionVoteRepository questionVoteRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public QuestionVoteDto addVoteToQuestion(QuestionVoteDto questionVoteDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(questionVoteDto.getUserId());
		Optional<Questions> optionalQuestion=questionRepository.findById(questionVoteDto.getQuestionId());
		
		if(optionalUser.isPresent() && optionalQuestion.isPresent())
		{
			QuestionVote questionVote=new QuestionVote();
			Questions existingQuestion=optionalQuestion.get();
			
			questionVote.setVoteType(questionVoteDto.getVoteType());
			questionVote.setQuestion(optionalQuestion.get());
			questionVote.setUser(optionalUser.get());
			questionVote.setVoteType(questionVoteDto.getVoteType());
			
			if(questionVote.getVoteType()==VoteType.UPVOTE) {
				existingQuestion.setVoteCount(existingQuestion.getVoteCount()+1);
			}
			else {
				existingQuestion.setVoteCount(existingQuestion.getVoteCount()-1);
			}
			questionRepository.save(existingQuestion);
			QuestionVote votedQuestion=questionVoteRepository.save(questionVote);
			
			QuestionVoteDto votedQuestionDto=new QuestionVoteDto();
			votedQuestionDto.setId(votedQuestion.getId());
			return votedQuestionDto;
		}
		return null;
	}
	
	
}
