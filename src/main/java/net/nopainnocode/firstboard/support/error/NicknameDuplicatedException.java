package net.nopainnocode.firstboard.support.error;

public class NicknameDuplicatedException extends RuntimeException{

	private final String nickname;

	public String getNickname() {
		return nickname;
	}

	public NicknameDuplicatedException(String nickname) {
		super();
		this.nickname = nickname;
	}
}
