package com.Library.Library_Management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.*;
//import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;
//import jakarta.persistence.Id; suggested by chatgpt to add but giving error




@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Userid;
    @NonNull
    private String userName;
    @NonNull
    @Email
    private String email;
    @NonNull
    private String password;
    private String role;

}
