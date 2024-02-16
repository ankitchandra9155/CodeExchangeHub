package com.backend.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.backend.dtos.QuestionDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="questions")
public class Questions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private Integer voteCount=0;

	@Lob
	@Column(name="body", length=512)
	private String body;
	
	private Date createdDate;
	
	@ElementCollection
	@CollectionTable(name="question_tags",joinColumns=@JoinColumn(name="question_id"))
	@Column(name="tags")
	private List<String> tags;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="user_id",nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy="question",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<QuestionVote> questionVoteList;
	
	

	public Questions(Long id, String title, String body, Date createdDate, List<String> tags, User user,List<QuestionVote> questionVoteList) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.createdDate = createdDate;
		this.tags = tags;
		this.user = user;
		this.questionVoteList=questionVoteList;
	}

	
	public Questions() {
		super();
	}

	

	public List<QuestionVote> getQuestionVoteList() {
		return questionVoteList;
	}


	public void setQuestionVoteList(List<QuestionVote> questionVoteList) {
		this.questionVoteList = questionVoteList;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Integer getVoteCount() {
		return voteCount;
	}


	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}


	//conerting Question to questionDTO
	public QuestionDto getQuestionDto() {
		QuestionDto questionDto=new QuestionDto();
		questionDto.setId(id);
		questionDto.setTitle(title);
		questionDto.setBody(body);
		questionDto.setTags(tags);
		questionDto.setUserId(user.getId());
		questionDto.setUsername(user.getName());
		questionDto.setCreatedDate(createdDate);
		questionDto.setVoteCount(voteCount);
		return questionDto;
	}
	
	

}
