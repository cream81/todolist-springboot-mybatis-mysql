package com.sample.todolist.domain.repository.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.todolist.domain.model.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
	User findById(long id);
	User findByUsername(String username);
}