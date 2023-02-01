package com.brotech.auth.service;

import com.brotech.auth.entity.User;
import com.brotech.auth.entity.UserDetailsImp;
import com.brotech.auth.payload.SignUp;
import com.brotech.auth.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String findUser(String s) {
        User user = userRepository.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return user.getRoles().toString();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        UserDetailsImp userDetailsImp = new UserDetailsImp(user);
        System.out.println(userDetailsImp.getPassword());
        return userDetailsImp;
    }

    public boolean userExists(SignUp signUp) {
        if (userRepository.findByEmail(signUp.getEmail()).isPresent()) {
            return true;
        }
        return false;
    }

    @Transactional
    public User regiserUser(SignUp signUp) {

        signUp.setPassword(passwordEncoder.encode(signUp.getPassword()));
        User user = userRepository.save(new User(signUp));
        return user;

    }
}
