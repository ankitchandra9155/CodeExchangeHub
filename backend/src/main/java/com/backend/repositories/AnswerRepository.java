package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entities.Answers;

@Repository
public interface AnswerRepository extends JpaRepository<Answers,Long> {

	List<Answers> findAllByQuestionId(Long questionId);

}
