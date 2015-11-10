package net.nopainnocode.firstboard.service.impl;

import net.nopainnocode.firstboard.domain.Board;
import net.nopainnocode.firstboard.domain.Comment;
import net.nopainnocode.firstboard.domain.User;
import net.nopainnocode.firstboard.repository.CommentRepository;
import net.nopainnocode.firstboard.repository.UserRepository;
import net.nopainnocode.firstboard.service.BoardService;
import net.nopainnocode.firstboard.service.CommentService;
import net.nopainnocode.firstboard.service.UserService;
import net.nopainnocode.firstboard.support.error.BoardNotFoundException;
import net.nopainnocode.firstboard.support.error.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by no_pain_no_code on 2015. 11. 5..
 */

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    @Override
    public Comment addNewComment(Long boardId, Comment newComment) {

        User user = userRepository.findOne(newComment.getUser().getUserId());
        newComment.setUser(user);

        Board board = boardService.findBoard(boardId);
        if (null == board)
            throw new BoardNotFoundException(boardId);
        else {
            board.getComments().add(newComment);
        }

        return commentRepository.save(newComment);
    }

    @Override
    public List<Comment> findComments(Long boardId) {

        // todo : check comments is null or not

        return boardService.findBoard(boardId).getComments();
    }

    @Override
    public List<Comment> findComments(String username) {

        User user = userService.findUser(username);

        // todo : check comment is null or not

        return commentRepository.findByUser(user);
    }

    @Override
    public Comment updateComment(Comment enteredComment) {

        return findCommentIfExist(enteredComment.getCommentId()).update(enteredComment);
    }

    @Override
    public boolean deleteComment(Long commentId) {

        if (null == findCommentIfExist(commentId))
            return false;
        else {
            commentRepository.delete(commentId);

            return true;
        }
    }

    private Comment findCommentIfExist(Long commentId) {

        Comment comment = commentRepository.findOne(commentId);

        // if comment does not exist
        if (null == comment)
            throw new CommentNotFoundException(commentId);

        return comment;
    }
}
