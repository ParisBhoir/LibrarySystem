package com.paris.librarySystem.dao;

import com.paris.librarySystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book b WHERE b.author_id=(select author_id from author where name=:name)",nativeQuery = true)
    List<Book> findAllByAuthorName(String name);


    Book findByTitle(String title);
}
