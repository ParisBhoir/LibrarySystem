package com.paris.librarySystem.dao;

import com.paris.librarySystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
