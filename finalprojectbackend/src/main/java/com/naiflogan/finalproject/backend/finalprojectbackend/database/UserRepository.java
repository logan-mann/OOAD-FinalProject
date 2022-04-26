package com.naiflogan.finalproject.backend.finalprojectbackend.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class to access user database, extends JpaRespository from Spring
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    
    //Function to insert user record into table
    @Modifying
    @Transactional
    @Query(value="INSERT into users (username, hashed_password) values (:username, :hashed_password)", nativeQuery=true)
    void createUser(@Param("username") String username, @Param("hashed_password") String hashedPassword);

    //Function to get user by username
    @Query(value="SELECT * FROM users WHERE username = :username", nativeQuery = true)
    UserEntity getUserByUsername(@Param("username") String username);
    
}
