package br.com.livetouch.email_manager.enums;

public enum Role {
	ROOT("ROOT"),
	ADMIN("ADMIN"),
	USER("USER");
	
	private final String name;
	
	private Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
