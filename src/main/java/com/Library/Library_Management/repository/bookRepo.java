package com.Library.Library_Management.repository;

import com.Library.Library_Management.entity.BookEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bookRepo extends JpaRepository<BookEntry,Long> {
    BookEntry findByTitle(String title);
}
