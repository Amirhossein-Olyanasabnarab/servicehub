package com.servicehub.provider;

import com.servicehub.user.Role;
import com.servicehub.user.User;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProviderProfileTest {

    @Test
    void shouldCreateProviderProfileForUser(){

        UUID userId = UUID.randomUUID();

        ProviderProfile profile = new ProviderProfile
                (userId, "Professional dog groomer");

        assertNotNull(profile.getId());
        assertEquals(userId, profile.getUserId());
        assertEquals("Professional dog groomer", profile.getBio());
        assertTrue(profile.isActive());
    }
}
