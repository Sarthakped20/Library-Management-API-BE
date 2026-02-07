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
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping("/create-user")
    public ResponseEntity<?>createUser(@RequestBody UserEntry userEntry){
        try {
            userService.createUser(userEntry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
//            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<?>getAllBooks(){
        try {
            List<BookEntry> allBooks = bookService.getAllBooks();
            if (allBooks.isEmpty()){
                return new ResponseEntity<>("No Books Found! ",HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allBooks,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error fetching Books : ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
