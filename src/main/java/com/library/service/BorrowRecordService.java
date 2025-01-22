package com.library.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.BorrowRecord;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRecordRepository;
import com.library.repository.UserRepository;

@Service
public class BorrowRecordService {

    private static final Logger logger = LoggerFactory.getLogger(BorrowRecordService.class);

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // Create a new borrow record
    public String borrowBook(Long userId, Long bookId) {
        logger.info("Creating borrow record for user {} and book {}", userId, bookId);

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setUserId(userId, userRepository);
        borrowRecord.setBookId(bookId, bookRepository);

        borrowRecordRepository.save(borrowRecord);
        logger.info("Borrow record created successfully with ID: {}", borrowRecord.getId());

        return "Book borrowed successfully";
    }

    // Get all borrow records for a user
    public List<BorrowRecord> getBorrowRecords(Long userId) {
        logger.info("Fetching borrow records for user with ID: {}", userId);
        return borrowRecordRepository.findByUserId(userId);
    }
}
