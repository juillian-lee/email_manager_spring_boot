package br.com.livetouch.email_manager.payload;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
}
