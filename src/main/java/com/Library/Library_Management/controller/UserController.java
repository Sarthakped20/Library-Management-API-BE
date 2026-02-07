package com.Library.Library_Management.controller;

import com.Library.Library_Management.entity.BookEntry;
import com.Library.Library_Management.entity.UserEntry;
import com.Library.Library_Management.service.BookService;
import com.Library.Library_Management.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    @GetMapping("/view/{id}")
    public ResponseEntity<?>Viewprofile(@PathVariable Long id, @RequestBody UserEntry userEntry){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long id = authentication.getid();
        try {
            UserEntry user = userService.getById(id);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error Fetching user",HttpStatus.INTERNAL_SERVER_ERROR);
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
    @GetMapping("/book/{id}")
    public ResponseEntity<?>getBookById(@PathVariable Long id){
        try {
            BookEntry bookById = bookService.getBookById(id);
            return new ResponseEntity<>(bookById,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/book/{title}")
//    public ResponseEntity<?>searchByTittle(@PathVariable String title){
//        try {
//            BookEntry bookById = bookService.getBookByTitle(title);
//            return new ResponseEntity<>(bookById,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }





}
