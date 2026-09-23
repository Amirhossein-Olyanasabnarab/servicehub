package com.servicehub.provider;

import java.util.UUID;

public class ProviderProfile {
    private final UUID id;
    private final UUID userId;

    private String bio;
    private boolean active;

    public ProviderProfile(UUID userId, String bio){
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.bio = bio;
        this.active = true;
    }

    public void activate(){
        this.active = true;
    }

    public void deactivate(){
        this.active = false;
    }

    public UUID getId(){
        return id;
    }
    public UUID getUserId(){
        return userId;
    }

    public String getBio(){
        return bio;
    }

    public boolean isActive(){
        return active;
    }
}
