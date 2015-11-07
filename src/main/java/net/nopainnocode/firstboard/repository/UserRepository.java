package net.nopainnocode.firstboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.nopainnocode.firstboard.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByNickname(String nickname);

}
