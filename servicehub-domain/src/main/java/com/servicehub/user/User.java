package com.servicehub.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private UUID id;
    private String fullName;
    private String email;
    private String passwordHash;
    private UserStatus status;
    private Set<Role> roles;


    public User
            (
                    String fullName,
                    String email,
                    String passwordHash,
                    Set<Role> roles
            ) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = UserStatus.ACTIVE;
        this.roles = new HashSet<>(roles);

        validateRoles();
    }

    private void validateRoles() {
        if (roles.isEmpty())
            throw new IllegalArgumentException("User must have at least one role.");

        if (roles.contains(Role.ADMIN) && roles.size() > 1)
            throw new IllegalArgumentException("ADMIN cannot have any other role.");
    }

    public void activate() {
        this.status = UserStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = UserStatus.INACTIVE;
    }

    public void block() {
        this.status = UserStatus.BLOCKED;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public Set<Role> getRoles(){
        return Set.copyOf(roles);
    }
}
