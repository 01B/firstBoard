package net.nopainnocode.firstboard.service.impl;

import net.nopainnocode.firstboard.domain.Board;
import net.nopainnocode.firstboard.repository.BoardRepository;
import net.nopainnocode.firstboard.service.BoardService;
import net.nopainnocode.firstboard.support.error.BoardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired private BoardRepository boardRepository;

    @Override
    public Board addNewBoard(Board newBoard) {

        return boardRepository.save(newBoard);
    }

    @Override
    public Board findBoard(Long boardId) {

        return findBoardIfExist(boardId);
    }


    @Override
    public Page<Board> findBoard(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    @Override
    public Board updateBoard(Board enteredBoard) {

        return findBoardIfExist(enteredBoard.getBoardId()).updateBoard(enteredBoard);
    }

    @Override
    public boolean deleteBoard(Long boardId) {

        if(null == findBoardIfExist(boardId))
            return false;
        else{
            boardRepository.delete(boardId);

            return true;
        }
    }

    private Board findBoardIfExist(Long boardId) {
        Board board = boardRepository.findOne(boardId);

        if(null == board)
            throw new BoardNotFoundException(boardId);

        return board;
    }
}
