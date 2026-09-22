package com.servicehub.user;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    @Test
    void shouldRejectUserWithoutRole(){
        assertThrows(
          IllegalArgumentException.class,
                () -> new User(
                 "Amir",
                 "amirholya@gmail.com",
                 "hashed-password",
                        Set.of()
                )
        );
    }

    @Test
    void shouldRejectAdminWithAnotherRole(){
        assertThrows(
                IllegalArgumentException.class,
                ()->new User(
                        "Amir",
                        "amirholya01@gmail,com",
                        "password-hashed",
                        Set.of(Role.ADMIN, Role.CUSTOMER)
                )
        );
    }

    @Test
    void shouldAllowCustomerAndProviderRoles(){
        User user = new User
                (
                  "Amir", "amirholya@gmail,com",
                  "password-hashed",
                  Set.of(Role.CUSTOMER, Role.PROVIDER)
                );

        assertEquals(
                Set.of(Role.CUSTOMER, Role.PROVIDER),
                user.getRoles()
        );
    }

}
