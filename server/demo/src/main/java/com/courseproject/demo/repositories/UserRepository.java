package com.courseproject.demo.repositories;

import com.courseproject.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByLogin(String login);

  User findByLoginAndPassword(String login, String password);
}