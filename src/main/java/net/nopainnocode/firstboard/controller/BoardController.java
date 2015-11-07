package net.nopainnocode.firstboard.controller;

import net.nopainnocode.firstboard.domain.Board;
import net.nopainnocode.firstboard.domain.Comment;
import net.nopainnocode.firstboard.service.BoardService;
import net.nopainnocode.firstboard.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by no_pain_no_code on 2015. 11. 4..
 */

@RestController
@RequestMapping(value = "/board")
public class BoardController {
    
    @Autowired private BoardService boardService;
    @Autowired private CommentService commentService;

    // create new board
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewBoard(@RequestBody @Valid Board board, BindingResult bindingResult){
        
        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());

        Board newBoard = boardService.addNewBoard(board);

        return ResponseEntity.status(HttpStatus.CREATED).body(newBoard);
    }

    /**
     *
     * @param boardId
     * @return
     */
    @RequestMapping(value = "/{boardId}", method = RequestMethod.GET)
    public ResponseEntity<?> findBoard(@PathVariable("boardId") Long boardId){

        Board board = boardService.findBoard(boardId);
        board.setComments(commentService.findComments(board.getBoardId()));

        return ResponseEntity.ok(board);
    }

    /**
     * 게시글을 수정합니다.
     * @param enteredBoard 수정된 게시글 객체
    * @param bindingResult 유효성 검사 결과
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBoard( @RequestBody @Valid Board enteredBoard, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());

        Board updatedBoard = boardService.updateBoard(enteredBoard);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedBoard);
    }

    /**
     * delete board
     * @param boardId the number of board id
     * @return
     */
    @RequestMapping(value = "/{boardId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBoard(@PathVariable("boardId") Long boardId){

        // delete comments
        boolean isCommentDeleted = deleteComments(boardId);

        if(boardService.deleteBoard(boardId) && isCommentDeleted)
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    /**
     * delete comments
     * @param boardId the number of board Id
     * @return the result of deleting comments
     */
    private boolean deleteComments(Long boardId) {

        boolean isCommentDeleted = true;

        for(Comment comment : boardService.findBoard(boardId).getComments()){
            if(!commentService.deleteComment(comment.getCommentId())) {
                isCommentDeleted = false;
                break;
            }
        }

        return isCommentDeleted;
    }
}
