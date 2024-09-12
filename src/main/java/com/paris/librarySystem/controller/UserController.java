package com.paris.librarySystem.controller;

import com.paris.librarySystem.model.Book;
import com.paris.librarySystem.model.BorrowRecord;
import com.paris.librarySystem.model.User;
import com.paris.librarySystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUSer(user);
    }

    @GetMapping("/borrowRecords/{id}")
    public List<BorrowRecord> getUserBorrowRecords(@PathVariable Long id) {
        return userService.getUserBorrowRecords(id);
    }

    @GetMapping("/borrow")
    public Optional<Book> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return userService.borrowBook(userId, bookId);
    }

    @PutMapping("/return")
    public void returnBook(@RequestParam Long userId, @RequestParam Long bookId){
        userService.returnBook(userId, bookId);
    }
}
