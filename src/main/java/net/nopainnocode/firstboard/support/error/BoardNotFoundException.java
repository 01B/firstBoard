package net.nopainnocode.firstboard.support.error;

/**
 * Created by no_pain_no_code on 2015. 11. 5..
 */
public class BoardNotFoundException extends RuntimeException {
    private final Long boardId;

    public BoardNotFoundException(Long boardId) {
        this.boardId = boardId;
    }
}
