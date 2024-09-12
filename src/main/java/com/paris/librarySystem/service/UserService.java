package com.paris.librarySystem.service;

import com.paris.librarySystem.controller.LibraryController;
import com.paris.librarySystem.dao.BorrowRecordRepository;
import com.paris.librarySystem.dao.UserRepository;
import com.paris.librarySystem.model.Book;
import com.paris.librarySystem.model.BorrowRecord;
import com.paris.librarySystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private LibraryController libraryController;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUSer(User user) {
        return userRepository.save(user);
    }

    public List<BorrowRecord> getUserBorrowRecords(Long id) {
        return borrowRecordRepository.findByUserId(id);
    }

    public Optional<Book> borrowBook(Long userId, Long bookId) {
        return libraryController.borrowBook(userId, bookId);
    }
}
