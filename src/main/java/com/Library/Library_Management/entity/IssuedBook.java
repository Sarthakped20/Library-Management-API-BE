package com.Library.Library_Management.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "IssueBook")
public class IssuedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Issueid;
    private UserEntry user;
    private BookEntry book;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private double penalty;

    private boolean returned = false;
}
