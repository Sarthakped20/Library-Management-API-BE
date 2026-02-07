package com.Library.Library_Management.controller;

import com.Library.Library_Management.entity.BookEntry;
import com.Library.Library_Management.entity.UserEntry;
import com.Library.Library_Management.service.BookService;
import com.Library.Library_Management.service.UserDetailsImpl;
import com.Library.Library_Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @GetMapping("/user-list")
    public ResponseEntity<?>getAll(){
        try {
            List<UserEntry> allUser = userService.getAllUser();
            if (allUser.isEmpty()){
                return new ResponseEntity<>("No Users Found !",HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allUser,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error Fetching Users !",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/user-create")
    public ResponseEntity<?>create(@RequestBody UserEntry userEntry){
        try {
            userService.createUser(userEntry);
            return new ResponseEntity<>(userEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error !",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/user-update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserEntry Updateduser){
        try {
            UserEntry User = userService.updateUser(id, Updateduser);

            if (User != null){
                return new ResponseEntity<>(User, HttpStatus.OK);
            }
            return new ResponseEntity<>("No user found !",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>("Error updating User !",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user-delete/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Long id){
        try {
            UserEntry user = userService.getById(id);
            if (user == null){
                return new ResponseEntity<>("No User Found !",HttpStatus.NOT_FOUND);
            }
            userService.deleteById(id);
            return new ResponseEntity<>("User Deleted! ",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error Deleting User !",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/book-update/{id}")
    public ResponseEntity<?>updateBook(@PathVariable Long id , @RequestBody BookEntry updateEntry){
        try {
            BookEntry Updated = bookService.updateBook(id,updateEntry);
            if (Updated != null){
                return new ResponseEntity<>(Updated,HttpStatus.OK);
            }
            return new ResponseEntity<>("No Book Found !",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Error Updating Book",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/book-list")
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

//  Add Book
    @PostMapping("/book-add")
    public ResponseEntity<?> addBook(@RequestBody BookEntry bookEntry){
        try {
            bookService.addBook(bookEntry);
            return new ResponseEntity<>(bookEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error adding Book !",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Delete Book
    @DeleteMapping("/book-delete/{id}")
    public ResponseEntity<?>deleteBook(@PathVariable Long id){
        try {
            BookEntry book = bookService.getBookById(id);
            if (book == null){
                return new ResponseEntity<>("No Book Found !",HttpStatus.NOT_FOUND);
            }
            bookService.deleteBookById(id);
            return new ResponseEntity<>("Book Deleted !",HttpStatus.OK);
//          return new ResponseEntity<>("User Not Found !",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Error Deleting Book !",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/admin-setRole")
    public ResponseEntity<?> updateRole(@PathVariable Long id,@RequestParam String role){
        try {
            UserEntry user = userService.getById(id);
            if (user == null){
                return new ResponseEntity<>("User Not Found! ",HttpStatus.NOT_FOUND);
            }
            userService.changeRole(id,role);
            return new ResponseEntity<>("Updated Role! ",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error Updating User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
