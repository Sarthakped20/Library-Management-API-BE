package com.Library.Library_Management.repository;

import com.Library.Library_Management.entity.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<UserEntry,Long> {
    UserEntry findByUserName(String userName);
}
