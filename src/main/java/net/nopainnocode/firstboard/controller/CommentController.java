package net.nopainnocode.firstboard.controller;

import net.nopainnocode.firstboard.domain.Comment;
import net.nopainnocode.firstboard.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by no_pain_no_code on 2015. 11. 5..
 */

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    @Autowired private CommentService commentService;

    /**
     * 게시글에 댓글을 입력합니다. 
     * @param boardId 게시글 id
     * @param enteredComment 댓글 객체
     * @param bindingResult 댓글 객체 유효성 검증 결
     * @return
     */
    @RequestMapping(value = "/{boardId}/comments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewComment(@PathVariable("boardId") Long boardId,
                                           @RequestBody @Valid Comment enteredComment,
                                           BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());

        Comment newComment = commentService.addNewComment(boardId, enteredComment);

        return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
    }

    /**
     * 입력된 댓글을 수정합니다.
     * @param enteredComment 수정된 댓글 객체
     * @param bindingResult 수정된 댓글 객체의 유효성 검증 결과
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateComment(@RequestBody @Valid Comment enteredComment,
                                           BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());

        Comment updatedComment = commentService.updateComment(enteredComment);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedComment);
    }

    /**
     * 입력된 댓글을 삭제합니다.
     * @param commentId 삭제하고자 하는 댓글 id
     * @return
     */
    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId){

        if(commentService.deleteComment(commentId))
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
