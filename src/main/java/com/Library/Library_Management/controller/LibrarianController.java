package com.Library.Library_Management.controller;

import com.Library.Library_Management.entity.BookEntry;
import com.Library.Library_Management.entity.UserEntry;
import com.Library.Library_Management.service.BookService;
import com.Library.Library_Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

//   get all users
    @GetMapping("/users")
    public ResponseEntity<?> getallUser(){
        try {
            List<UserEntry> allusers = userService.getAllUser();
            if (allusers.isEmpty()){
                return new ResponseEntity<>("No users Found!",HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allusers,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error Fetching users ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    get user by ID
    @GetMapping("/user/{id}")
    public ResponseEntity<?>getUser(@PathVariable Long id){
        try {
            UserEntry user = userService.getById(id);
            if (user == null){
                return new ResponseEntity<>("User Not Found !",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error fetching User! ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get List of all Books
    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(){
        try {
            List<BookEntry> allBooks = bookService.getAllBooks();
            if (allBooks.isEmpty()){
                return new ResponseEntity<>("No Books Found! ", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allBooks,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error fetching Books : ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
// Get Book by id
    @GetMapping("/book/{id}")
    public ResponseEntity<?>getBookById(@PathVariable Long id){
        try {
            BookEntry book = bookService.getBookById(id);
            if (book == null ){
                return new ResponseEntity<>("Book Not Found !",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(book,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    Update Book Details
    @PutMapping("/update-book/{id}")
    public ResponseEntity<?>updateBook(@PathVariable Long id , @RequestBody BookEntry updateEntry){
        try {
            BookEntry Updated = bookService.updateBook(id,updateEntry);
                if (updateEntry != null){
                    return new ResponseEntity<>(Updated,HttpStatus.OK);
            }
                return new ResponseEntity<>("No Book Found !",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Error Updating Book",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
