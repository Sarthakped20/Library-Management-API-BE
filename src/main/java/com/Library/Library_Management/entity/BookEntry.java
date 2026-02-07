package com.Library.Library_Management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
//import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookEntry {
    @Id  // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for indexing
    private Long Bookid;
    @NonNull
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private Integer totalCopies; // for sql/JPA we use Integer instead of int.
    private Integer availableCopies;
}
