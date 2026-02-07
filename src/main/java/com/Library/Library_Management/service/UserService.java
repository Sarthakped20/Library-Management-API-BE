package com.Library.Library_Management.service;

import com.Library.Library_Management.entity.UserEntry;
import com.Library.Library_Management.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

//    Create user
//    Get user by ID
//    Update user
//    Delete user
//    Get all users

//    private static final PasswordEncoder passwordenc = new BCryptPasswordEncoder();
//    public void saveEntry(UserEntry Userentry){
//        Userentry.setPassword(passwordenc.encode(Userentry.getPassword()));
//        Userentry.setRoles(Arrays.asList("User"));
//        userrepo.save(Userentry);
//    }
    @Autowired
    private userRepo userRepo;
    @Autowired
    private UserDetailsImpl userDetails;

    private static final PasswordEncoder passwordenc = new BCryptPasswordEncoder();


    public void createUser (UserEntry userEntry){
        userEntry.setRole("user"); // this says that default Role will be User
        userEntry.setPassword(passwordenc.encode(userEntry.getPassword()));
        userRepo.save(userEntry);
    }

    public UserEntry getById(Long Userid){
        return userRepo.findById(Userid).orElse(null);
    }
    public List<UserEntry> getAllUser() {
        return userRepo.findAll();

    }

    public void deleteUser(UserEntry userEntry){
        userRepo.delete(userEntry);
    }
    public void deleteById(Long id){
        userRepo.deleteById(id);
    }

    public UserEntry updateUser(Long id , UserEntry updatedUser){
        UserEntry existing = getById(id);
        if (existing == null){
            return null;
        }
        existing.setUserName(updatedUser.getUserName());
        existing.setEmail(updatedUser.getEmail());
        existing.setPassword(updatedUser.getPassword());
        existing.setRole(updatedUser.getRole());
        return userRepo.save(existing);
    }


//    Method to update the Role of an user
    public UserEntry changeRole(Long id , String role){
        UserEntry user = userRepo.findById(id).orElse(null);
        if (user == null){
            return null;
        }
        user.setRole(role);
        return userRepo.save(user);
    }


}
