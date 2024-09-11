package com.paris.librarySystem.service;

import com.paris.librarySystem.dao.*;
import com.paris.librarySystem.model.Author;
import com.paris.librarySystem.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBooksByAuthorName(String name){
        return bookRepository.findAllByAuthorName(name);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void addBook(List<Book> books) {
        for (Book book : books){
            addBookWithAuthor(book);
        }
    }

    @Transactional
    public Book addBookWithAuthor(Book book) {
        Author author = book.getAuthor();

        if (author.getId() == null) {
            // Check if author already exists by name
            Optional<Author> existingAuthor = authorRepository.findByName(author.getName());
            if (existingAuthor.isPresent()) {
                book.setAuthor(existingAuthor.get());
            } else {
                // Create a new author
                authorRepository.save(author);
            }
        } else {
            // Fetch the existing author by ID
            Optional<Author> existingAuthor = authorRepository.findById(author.getId());
            existingAuthor.ifPresent(book::setAuthor);
        }

        return bookRepository.save(book);
    }


    public void borrowBook(Long userId, Long bookId) {
        // Implement borrowing logic here
        // For example, check if the book is available and update BorrowRecord
    }

    public Optional<Book> deleteBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        bookRepository.deleteById(id);
        return book;
    }

    public Book updateBookById(Book book) {
        bookRepository.save(book);
        return book;
    }
}
