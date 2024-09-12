package com.paris.librarySystem.controller;

import com.paris.librarySystem.model.Author;
import com.paris.librarySystem.model.Book;
import com.paris.librarySystem.model.BorrowRecord;
import com.paris.librarySystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    //fetch all Authors
    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return libraryService.getAllAuthors();
    }

    //fetch all the books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    //fetch book by title
    @GetMapping("/book/{title}")
    public Book getAllBookByTitle(@PathVariable String title){
        return libraryService.getBookByTitle(title);
    }

    //fetch books by author
    @GetMapping("/books/{name}")
    public List<Book> getAllBooksByAuthorName(@PathVariable String name){
        return libraryService.getBooksByAuthorName(name);
    }

    //add books
    @PostMapping("/books")
    public void addBook(@RequestBody List<Book> books) {
        libraryService.addBook(books);
    }

    //add book
    @PostMapping("/bookWithAuthor")
    public ResponseEntity<Book> addBookWithAuthor(@RequestBody Book book) {
        Book createdBook = libraryService.addBookWithAuthor(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    //delete book
    @DeleteMapping("/book/{id}")
    public Optional<Book> deleteBook(@PathVariable Long id){
        return libraryService.deleteBookById(id);
    }

    //update book
    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book){
        return libraryService.updateBookById(book);
    }

    @GetMapping("/borrowRecords")
    public List<BorrowRecord> getAllRecords(){
        return libraryService.getAllRecords();
    }

    @GetMapping("/borrow")
    public Optional<Book> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return libraryService.borrowBook(userId, bookId);
    }
}
