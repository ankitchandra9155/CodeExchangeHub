package com.backend.services.answer;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dtos.AnswerDto;
import com.backend.entities.Answers;
import com.backend.entities.Questions;
import com.backend.entities.User;
import com.backend.repositories.AnswerRepository;
import com.backend.repositories.QuestionRepository;
import com.backend.repositories.UserRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Override
	public AnswerDto postAnswer(AnswerDto answerDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(answerDto.getUserId());
		Optional<Questions> optionalQuestions=questionRepository.findById(answerDto.getQuestionId());
		if(optionalUser.isPresent() && optionalQuestions.isPresent())
		{
			Answers answer=new Answers();
			answer.setBody(answerDto.getBody());
			answer.setCreatedDate(new Date());
			answer.setUser(optionalUser.get());
			answer.setQuestion(optionalQuestions.get());
			Answers createdAnswer=answerRepository.save(answer);
			
			AnswerDto createdAnswerDto=new AnswerDto();
			createdAnswerDto.setId(createdAnswer.getId());
			return createdAnswerDto;
			
		}
		return null;
	}

}
