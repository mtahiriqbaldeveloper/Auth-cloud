package com.brotech.auth.payload;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

    private String email;
    private String Password;

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
