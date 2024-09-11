package com.paris.librarySystem.controller;

import com.paris.librarySystem.model.Author;
import com.paris.librarySystem.model.Book;
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
    @GetMapping("/get/authors")
    public List<Author> getAllAuthors() {
        return libraryService.getAllAuthors();
    }

    //fetch all the books
    @GetMapping("/get/books")
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    //fetch book by title
    @GetMapping("/get/book/{title}")
    public Book getAllBookByTitle(@PathVariable String title){
        return libraryService.getBookByTitle(title);
    }

    //fetch books by author
    @GetMapping("/get/books/{name}")
    public List<Book> getAllBooksByAuthorName(@PathVariable String name){
        return libraryService.getBooksByAuthorName(name);
    }

    //add book
    @PostMapping("/add/book")
    public void addBook(@RequestBody Book book) {
        libraryService.addBook(book);
    }

    //add book
    @PostMapping("/add/bookWithAuthor")
    public ResponseEntity<Book> addBookWithAuthor(@RequestBody Book book) {
        Book createdBook = libraryService.addBookWithAuthor(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @DeleteMapping("/delete/book/{id}")
    public Optional<Book> deleteBook(@PathVariable Long id){
        return libraryService.deleteBookById(id);
    }


}
