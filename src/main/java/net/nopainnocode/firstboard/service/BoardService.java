package net.nopainnocode.firstboard.service;

import net.nopainnocode.firstboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by no_pain_no_code on 2015. 11. 3..
 */
public interface BoardService {

    Board addNewBoard(Board board);

    Board findBoard(Long boardId);

    Page<Board> findBoard(Pageable pageable);

    Page<Board> findBoard(Pageable pageable, String username);

    Board updateBoard(Board board);

    boolean deleteBoard(String username);

    boolean deleteBoard(Long boardId);
}
