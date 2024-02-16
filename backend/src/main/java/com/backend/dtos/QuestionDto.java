package com.backend.dtos;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuestionDto {
	
	private Long id;
	private String title;
	private String body;
	@JsonProperty("tags")
	private List<String> tags;
	private Long userId;
	private String username;
	private Date createdDate;
	private Integer voteCount;
	private int voted;
	
	
	
	
	
	public QuestionDto(Long id, String title, String body, List<String> tags, Long userId, String username,
			Date createdDate,Integer voteCount,int voted) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.tags = tags;
		this.userId = userId;
		this.username = username;
		this.createdDate = createdDate;
		this.voteCount=voteCount;
		this.voted=voted;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public QuestionDto() {
		super();
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
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	  
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	


	public Integer getVoteCount() {
		return voteCount;
	}


	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}
	
	


	public int getVoted() {
		return voted;
	}


	public void setVoted(int voted) {
		this.voted = voted;
	}


	@Override
	public String toString() {
		return "QuestionDto [id=" + id + ", title=" + title + ", body=" + body + ", tags=" + tags + ", userId=" + userId
				+ "]";
	}
	
	
	

}
