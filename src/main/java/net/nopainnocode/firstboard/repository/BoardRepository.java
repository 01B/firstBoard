package net.nopainnocode.firstboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.nopainnocode.firstboard.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
