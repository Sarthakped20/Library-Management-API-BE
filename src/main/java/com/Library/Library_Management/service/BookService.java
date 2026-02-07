package com.Library.Library_Management.service;

import com.Library.Library_Management.entity.BookEntry;
import com.Library.Library_Management.repository.bookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Add book
//Get book by ID
//Update book
//Delete book
//Get all books

@Service
public class BookService {
    @Autowired
    private bookRepo bookRepo;


//    public void addBook(BookEntry bookEntry){
//        bookEntry.setAvailableCopies(bookEntry.getTotalCopies());
//        bookRepo.save(bookEntry);
//    }
    public void addBook(BookEntry bookEntry){
        bookEntry.setTotalCopies(bookEntry.getTotalCopies()+1);
        bookEntry.setAvailableCopies(bookEntry.getAvailableCopies()+1);
        bookRepo.save(bookEntry);
    }
    public List<BookEntry>getAllBooks(){
        return bookRepo.findAll();
    }
    public BookEntry getBookById(Long id){
        return bookRepo.findById(id).orElse(null);// we can use optional to avoid null checks.
    }
// No use because book is deleted only by its ID
//    public void deleteBook(BookEntry bookEntry){
//        bookRepo.delete(bookEntry);
//    }
    public void deleteBookById(Long id){
        bookRepo.deleteById(id);

    }

    public BookEntry updateBook(Long id , BookEntry updatedBook){
        BookEntry existingBook = bookRepo.getById(id);
        if (existingBook == null)return null;
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setTotalCopies(updatedBook.getTotalCopies());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());

        return bookRepo.save(existingBook);

    }


    public BookEntry getBookByTitle(String title) {
        return bookRepo.findByTitle(title);
    }
}
