package com.paris.librarySystem.dao;

import com.paris.librarySystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
