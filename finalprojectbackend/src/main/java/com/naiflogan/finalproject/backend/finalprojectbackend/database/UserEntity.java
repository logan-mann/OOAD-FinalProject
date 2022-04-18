package com.naiflogan.finalproject.backend.finalprojectbackend.database;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String username;

    @Column(name="hashed_password")
    private String hashedPassword;

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
