package com.paris.librarySystem.dao;

import com.paris.librarySystem.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUserId(Long userId);

    @Query(value = "SELECT * FROM borrow_record b WHERE b.user_id=:userId AND b.book_id=:bookId",nativeQuery = true)
    Optional<BorrowRecord> findByUserIdAndBookId(Long userId, Long bookId);
}
