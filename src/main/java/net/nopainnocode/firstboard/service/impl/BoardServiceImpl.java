package net.nopainnocode.firstboard.service.impl;

import net.nopainnocode.firstboard.domain.Board;
import net.nopainnocode.firstboard.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by no_pain_no_code on 2015. 11. 5..
 */
@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    @Override
    public Board addNewBoard(Board board) {
        return null;
    }

    @Override
    public Board findBoard(Long boardId) {
        return null;
    }

    @Override
    public Page<Board> findBoard(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Board> findBoard(Pageable pageable, String username) {
        return null;
    }

    @Override
    public Board updateBoard(Board board) {
        return null;
    }

    @Override
    public boolean deleteBoard(String username) {
        return false;
    }

    @Override
    public boolean deleteBoard(Long boardId) {
        return false;
    }
}
