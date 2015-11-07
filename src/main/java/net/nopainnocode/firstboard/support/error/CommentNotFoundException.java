package net.nopainnocode.firstboard.support.error;

/**
 * Created by no_pain_no_code on 2015. 11. 5..
 */
public class CommentNotFoundException extends RuntimeException{
    private final Long commentId;

    public CommentNotFoundException(Long commentId){
        this.commentId = commentId;
    }
}
