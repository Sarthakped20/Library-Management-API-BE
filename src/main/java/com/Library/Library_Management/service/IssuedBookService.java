package com.Library.Library_Management.service;

import com.Library.Library_Management.entity.IssuedBook;
import com.Library.Library_Management.entity.UserEntry;
import com.Library.Library_Management.repository.bookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Library.Library_Management.entity.BookEntry;
import com.Library.Library_Management.repository.userRepo;
import com.Library.Library_Management.repository.issuedBookRepo;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class IssuedBookService {
    @Autowired
    private issuedBookRepo issuedBookRepo;
    @Autowired
    private bookRepo bookRepo;

    @Autowired
    private userRepo userRepo;
    private static final int perdayfine = 10;


    public IssuedBook issuedBook(Long Userid,Long Bookid){
        UserEntry user = userRepo.findById(Userid)
                .orElseThrow(()->new RuntimeException("No user Found! "));

        BookEntry book = bookRepo.findById(Bookid)
                .orElseThrow(()->new RuntimeException("No user Found! "));

        if (book.getAvailableCopies()<1){
            throw new RuntimeException("Book unavailable!");
        }

        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepo.save(book);

        IssuedBook issuedBook = new IssuedBook();
        issuedBook.setUser(user);
        issuedBook.setBook(book);
        issuedBook.setIssueDate(LocalDate.now());
        issuedBook.setReturnDate(LocalDate.now().plusDays(14));

        return issuedBookRepo.save(issuedBook);
    }

    public void returnBook(Long Issueid){
        IssuedBook issuedBook = issuedBookRepo.findById(Issueid).orElseThrow(()->new RuntimeException("Not found!"));
//        idar se remaining part hai next time continue
        BookEntry book = issuedBook.getBook();
        issuedBook.setReturned(true);// updates the returned status of book
        issuedBook.setReturnDate(LocalDate.now());//set returned the date and time
        LocalDate today = LocalDate.now();
        LocalDate due = issuedBook.getDueDate();
        double penalty = 0.0;
        if (today.isAfter(due)){
            long extended = ChronoUnit.DAYS.between(due, today);
            penalty = extended*perdayfine;
        }
        issuedBook.setPenalty(penalty);
        issuedBookRepo.save(issuedBook);// saves the issued book
        bookRepo.save(book);//saves the book and updates the number of available copies
    }
}
