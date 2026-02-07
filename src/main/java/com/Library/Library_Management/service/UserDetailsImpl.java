package com.Library.Library_Management.service;

import com.Library.Library_Management.entity.UserEntry;
import com.Library.Library_Management.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    private userRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntry userdetails = userRepo.findByUserName(username);

        if (userdetails != null){

            return User.builder()
                    .username(userdetails.getUserName())
                    .password(userdetails.getPassword())
                    .roles(userdetails.getRole())
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: "+username);
    }
}
