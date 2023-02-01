package com.brotech.auth.expection;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class AccessDeniedException implements AuthenticationFailureHandler {

    Logger logger = Logger.getLogger(AccessDeniedException.class.getName());


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("failed the login");
        response.sendRedirect("/helloworld");
    }
}
