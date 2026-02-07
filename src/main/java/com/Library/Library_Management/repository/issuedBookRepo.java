package com.Library.Library_Management.repository;

import com.Library.Library_Management.entity.BookEntry;
import com.Library.Library_Management.entity.IssuedBook;
import com.Library.Library_Management.entity.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//public interface issuedBookRepo extends JpaRepository<IssuedBook,Long> {
//    boolean existsByBookandUserReturnedFalse(BookEntry bookEntry, UserEntry userEntry){
//        issuedBook bookreturned(bookEntry,userEntry);
//    }
//}
public interface issuedBookRepo extends JpaRepository<IssuedBook, Long> {

    boolean existsByBookAndUserAndReturnedFalse(BookEntry book, UserEntry user);

    Optional<IssuedBook> findByBookIdAndUserIdAndReturnedFalse(Long bookId, Long userId);
}
