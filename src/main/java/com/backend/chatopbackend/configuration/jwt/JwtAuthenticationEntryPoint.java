package com.backend.chatopbackend.configuration.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest resquest, HttpServletResponse response,
                         AuthenticationException authenticationException) throws IOException, IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{ \"message\": \"" +  authenticationException.getMessage() + "\" }");
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("JWT Authentication");
        super.afterPropertiesSet();
    }
}
