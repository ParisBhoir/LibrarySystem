package com.paris.librarySystem.dao;

import com.paris.librarySystem.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
}
