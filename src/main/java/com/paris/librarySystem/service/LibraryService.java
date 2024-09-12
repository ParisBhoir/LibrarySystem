package com.paris.librarySystem.service;

import com.paris.librarySystem.dao.*;
import com.paris.librarySystem.model.Author;
import com.paris.librarySystem.model.Book;
import com.paris.librarySystem.model.BorrowRecord;
import com.paris.librarySystem.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Optional<Book> deleteBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        bookRepository.deleteById(id);
        return book;
    }

    public Book updateBookById(Book book) {
        bookRepository.save(book);
        return book;
    }

    public List<BorrowRecord> getAllRecords() {
        return borrowRecordRepository.findAll();
    }



    public Optional<Book> borrowBook(Long userId, Long bookId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (userOpt.isPresent() && bookOpt.isPresent()){
            User user = userOpt.get();
            Book book = bookOpt.get();

            if (book.getNumberOfCopies() > 0){
                book.setNumberOfCopies(book.getNumberOfCopies()-1);
                bookRepository.save(book);

                BorrowRecord record = new BorrowRecord();
                record.setBook(book);
                record.setUser(user);
                record.setBorrowDateTime(LocalDateTime.now());
                borrowRecordRepository.save(record);
                System.out.println("Book borrowed sucessfully");
            }else {
                System.out.println("Book is not available currently");
                throw new IllegalArgumentException("Book is not available currently");
            }

        }else {
            System.out.println("Book or user not found");
            throw new IllegalArgumentException("Book or user not found");
        }
        return bookOpt;
    }

    public void returnBook(long userId, long bookId) {
        Optional<BorrowRecord> record = borrowRecordRepository.findByUserIdAndBookId(userId,bookId);

        if (record.isPresent()){
            Optional<Book> bookopt =bookRepository.findById(bookId);
            if (bookopt.isPresent()){
                Book book = bookopt.get();
                book.setNumberOfCopies(book.getNumberOfCopies()+1);

                borrowRecordRepository.delete(record.get());
                System.out.println("Book Returned");
            }else {
                System.out.println("Book not found but record present");
                throw new IllegalArgumentException("Book not found but record present");
            }

        }else {
            System.out.println("Book or user BorrowRecord not found");
            throw new IllegalArgumentException("Book or user BorrowRecord not found");
        }
    }
}
