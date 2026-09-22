package com.servicehub.user;

import java.util.UUID;

public class User {
    private UUID id;
    private String fullName;
    private String email;
    private String passwordHash;
    private UserStatus status;

    public User
            (
                String fullName,
                String email,
                String passwordHash
            ){
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = UserStatus.ACTIVE;
    }

    public void activate(){
        this.status = UserStatus.ACTIVE;
    }
    public void deactivate(){
        this.status = UserStatus.INACTIVE;
    }
    public void block(){
        this.status = UserStatus.BLOCKED;
    }

    public UUID getId(){
        return id;
    }

    public String getFullName(){
        return fullName;
    }
    public String getEmail(){
        return email;
    }

    public UserStatus getStatus(){
        return status;
    }
}
