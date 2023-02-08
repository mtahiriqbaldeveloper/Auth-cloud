package com.brotech.auth.controller;


import com.brotech.auth.jwt.JwtUtils;
import com.brotech.auth.payload.Login;
import com.brotech.auth.payload.SignUp;
import com.brotech.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RestController
@RequestMapping(path = "/auth/")
@CrossOrigin("http://localhost:3000/")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    Logger logger = Logger.getLogger(AuthController.class.getName());

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody Login login) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        logger.info(token);
        return ResponseEntity.ok(token);
    }

    @PostMapping("register")
    public ResponseEntity registerUser(@RequestBody SignUp signUp) {

        logger.info(signUp.toString());
        if (userService.userExists(signUp)) {
            logger.info("user already exists");
            return new ResponseEntity("EXISTS", HttpStatus.BAD_REQUEST);
        }else {
            userService.regiserUser(signUp);
            return new ResponseEntity("SUCCESS",HttpStatus.ACCEPTED);
        }
    }


}
