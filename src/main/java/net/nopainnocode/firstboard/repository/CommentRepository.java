package net.nopainnocode.firstboard.repository;

import net.nopainnocode.firstboard.domain.Comment;
import net.nopainnocode.firstboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUser(User user);
}
