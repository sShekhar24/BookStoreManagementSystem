package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.BorrowRecord;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    // Custom query method to find borrow records by user ID
    List<BorrowRecord> findByUserId(Long userId);
}
