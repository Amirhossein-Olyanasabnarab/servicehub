package com.servicehub.provider;

import com.servicehub.user.Role;
import com.servicehub.user.User;
import com.servicehub.user.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateProviderProfileUseCaseTest {

    @Test
    void shouldCreateProviderProfileForProviderUser(){
        UUID userId = UUID.randomUUID();

        User user = new User(
                "Amir",
                "amir@example.com",
                "hashed-password",
                Set.of(Role.PROVIDER)
        );

        UserRepository userRepository = id -> Optional.of(user);

        CreateProviderProfileUseCase useCase =
                new CreateProviderProfileUseCase(userRepository);

        ProviderProfile profile =
                useCase.execute(
                        userId,
                        "Professional dog groomer"
                );

        assertNotNull(profile);
        assertEquals(userId, profile.getUserId());
        assertEquals(
                "Professional dog groomer",
                profile.getBio()
        );
        assertTrue(profile.isActive());
    }

    @Test
    void shouldRejectWhenUserDoesNotExist() {

        UUID userId = UUID.randomUUID();

        UserRepository userRepository =
                id -> Optional.empty();

        CreateProviderProfileUseCase useCase =
                new CreateProviderProfileUseCase(userRepository);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> useCase.execute(
                                userId,
                                "Professional dog groomer"
                        )
                );

        assertEquals(
                "User not found.",
                exception.getMessage()
        );
    }

    @Test
    void shouldRejectNonProviderUser() {

        UUID userId = UUID.randomUUID();

        User user = new User(
                "Amir",
                "amir@example.com",
                "hashed-password",
                Set.of(Role.CUSTOMER)
        );

        UserRepository userRepository =
                id -> Optional.of(user);

        CreateProviderProfileUseCase useCase =
                new CreateProviderProfileUseCase(userRepository);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(
                        userId,
                        "Professional dog groomer"
                )
        );

        assertEquals(
                "User is not provider.",
                exception.getMessage()
        );
    }
}
