package com.back_end.project_management_system.dto;

import javax.validation.constraints.NotBlank;

public class ForgotPasswordDTO {
	
	@NotBlank(message = "Username can't be null")
	private String username;
	
	@NotBlank(message = "Security question be null")
	private int securityQuestionId;
	
	@NotBlank(message = "Security answer be null")
	private String securityAnswer;

	public ForgotPasswordDTO(String username, int securityQuestionId, String securityAnswer) {
		super();
		this.username = username;
		this.securityQuestionId = securityQuestionId;
		this.securityAnswer = securityAnswer;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSecurityQuestionId() {
		return securityQuestionId;
	}
	public void setSecurityQuestionId(int securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

}
