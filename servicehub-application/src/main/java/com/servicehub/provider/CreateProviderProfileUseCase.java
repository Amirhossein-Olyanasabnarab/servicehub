package com.servicehub.provider;

import com.servicehub.user.Role;
import com.servicehub.user.User;
import com.servicehub.user.UserRepository;

import java.util.UUID;

public class CreateProviderProfileUseCase {

    private final UserRepository userRepository;
    public CreateProviderProfileUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ProviderProfile execute(UUID userId, String bio){
        User user = userRepository.findById(userId)
                .orElseThrow(
                        ()-> new IllegalArgumentException("User not found.")
                        );

        if (!user.getRoles().contains(Role.PROVIDER)){
            throw new IllegalArgumentException
                    ("User is not provider.");
        }

        return new ProviderProfile(userId, bio);
    }
}
