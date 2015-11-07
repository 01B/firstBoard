package net.nopainnocode.firstboard.support.error;

public class UsernameDuplicatedException extends RuntimeException{
	
	private final String username;

	public String getUsername() {
		return username;
	}

	public UsernameDuplicatedException(String username) {
		super();
		this.username = username;
	}
	
}
