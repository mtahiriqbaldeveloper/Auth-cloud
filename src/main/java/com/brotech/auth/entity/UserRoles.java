package com.brotech.auth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    @JsonProperty("Admin")
    Role_Admin,

    @JsonProperty("Client")
    Role_Client;

    @Override
    public String getAuthority() {
        return name();
    }
}
