package com.back_end.project_management_system.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.back_end.project_management_system.entity.SecurityQuestion;

public class RegisterUserDTO {
	
	@NotBlank(message = "Username should not be null")
	@Email(message = "Invalid username format")
	private String username;
	
	@NotBlank(message = "Password should not be null")
	@Size(min = 5, message = "Password must contain atleast 5 characters")
	private String password;
	
	private String name;
	
	@NotNull(message = "Security question should not be null")
	private SecurityQuestion securityQuestion;
	
	@NotBlank(message = "Security answer should not be null")
	private String securityAnswer;

	public RegisterUserDTO(
			@NotBlank(message = "Username should not be null") @Email(message = "Invalid username format") String username,
			@NotBlank(message = "Password should not be null") @Size(min = 5, message = "Password must contain atleast 5 characters") String password,
			String name, @NotNull(message = "Security question should not be null") SecurityQuestion securityQuestion,
			@NotBlank(message = "Security answer should not be null") String securityAnswer) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SecurityQuestion getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(SecurityQuestion securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
