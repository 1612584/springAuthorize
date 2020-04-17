package com.example.demo.filter;

import com.example.demo.model.AccountCredentials;
import com.example.demo.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public JWTLoginFilter(String url, AuthenticationManager authManager,BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(new AntPathRequestMatcher(url));
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        setAuthenticationManager(authManager);
    }

    //auto authenticate login request, bscrypt providing by AuthenticationManager in WebSecurityConfig, support userDetailsService
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
            AccountCredentials credentials = new AccountCredentials(request.getParameter("username"),request.getParameter("password"));

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(response, authResult.getName());
    }
}
