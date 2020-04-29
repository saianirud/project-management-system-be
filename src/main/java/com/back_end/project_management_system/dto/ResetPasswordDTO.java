package com.back_end.project_management_system.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ResetPasswordDTO {

	@NotBlank(message = "Reset password token should be null")
	private String token;
	
	@NotBlank(message = "Password should not be null")
	@Size(min = 5)
	private String password;

	public ResetPasswordDTO(@NotBlank(message = "Reset password token should ne null") String token,
			@NotBlank(message = "Password should not be null") String password) {
		super();
		this.token = token;
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
