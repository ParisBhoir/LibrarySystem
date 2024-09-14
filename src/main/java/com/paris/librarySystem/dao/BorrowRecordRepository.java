package com.paris.librarySystem.dao;

import com.paris.librarySystem.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUserId(Long userId);

    @Query(value = "SELECT * FROM borrow_record b WHERE b.borrow_date_time=(SELECT MAX(b1.borrow_date_time) FROM borrow_record b1 WHERE b1.user_id=:userId AND b1.book_id=:bookId)",nativeQuery = true)
    Optional<BorrowRecord> findByUserIdAndBookId(Long userId, Long bookId);
}
