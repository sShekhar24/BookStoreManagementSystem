package com.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Book;
import com.library.entity.BorrowRecord;
import com.library.repository.BorrowRecordRepository;

@Service
public class AuditingService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    // Method to log borrowing actions
    public void logBorrowingAction(BorrowRecord borrowRecord) {
        System.out.println("Book borrowed: " + borrowRecord.getBook().getTitle());
    }

    // Method to log returning actions
    public void logReturningAction(Book book) {
        System.out.println("Book returned: " + book.getTitle());
    }
}
