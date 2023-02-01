package com.brotech.auth.entity;


import com.brotech.auth.payload.SignUp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    @JsonIgnore
    private String password;

    //    @OneToMany(mappedBy = "user")
    @ElementCollection
    private List<UserRoles> roles;

    public User(SignUp user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        password = user.getPassword();
        roles = user.getRoles().stream().collect(Collectors.toList());
    }

    public User() {

    }
}
