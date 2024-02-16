package com.backend.dtos;

public class SignupDTO {

	private Long id;
	private String name;
	private String password;
	private String email;
	
	public SignupDTO(Long id, String name, String password, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "SignupDTO [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + "]";
	}

	public SignupDTO() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
