package net.nopainnocode.firstboard.service;

import net.nopainnocode.firstboard.domain.Comment;

import java.util.List;

/**
 * Created by no_pain_no_code on 2015. 11. 4..
 */
public interface CommentService {

    Comment addNewComment(Long boardId, Comment comment);

    List<Comment> findComments(Long boardId);

    List<Comment> findComments(String username);

    Comment updateComment(Comment comment);

    boolean deleteComment(Long commentId);
}
