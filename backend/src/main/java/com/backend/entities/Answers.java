package com.backend.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.backend.dtos.AnswerDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Answers {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name="body",length=512)
	private String body;
	
	private Date createdDate;

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="user_id",nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="question_id",nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	private Questions question;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public AnswerDto getAnswerDto() {
		// TODO Auto-generated method stub
		AnswerDto answerDto=new AnswerDto();
		answerDto.setBody(body);
		answerDto.setId(id);
		answerDto.setQuestionId(question.getId());
		answerDto.setUserId(user.getId());
		answerDto.setUsername(user.getName());
		answerDto.setCreatedDate(createdDate);
		return answerDto;
	}
	
	
}
