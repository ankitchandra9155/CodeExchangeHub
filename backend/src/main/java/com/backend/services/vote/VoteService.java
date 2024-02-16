package com.backend.services.vote;

import com.backend.dtos.QuestionVoteDto;

public interface VoteService {

	public QuestionVoteDto addVoteToQuestion(QuestionVoteDto questionVoteDto);
}
