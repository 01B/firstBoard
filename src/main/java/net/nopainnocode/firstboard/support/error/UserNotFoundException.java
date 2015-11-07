package net.nopainnocode.firstboard.support.error;

/**
 * Created by no_pain_no_code on 2015. 11. 3..
 */
public class UserNotFoundException extends RuntimeException {

    private Long userId;
    private String username;
    public UserNotFoundException(Long userId) {
        this.userId = userId;
    }

    public UserNotFoundException(String username){
        this.username = username;
    }
}
