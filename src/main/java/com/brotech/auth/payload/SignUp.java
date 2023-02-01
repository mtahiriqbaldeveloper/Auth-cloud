package com.brotech.auth.payload;


import com.brotech.auth.entity.UserRoles;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SignUp {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private List<UserRoles> roles = List.of(UserRoles.Role_Client);

    @Override
    public String toString() {
        return "SignUp{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
