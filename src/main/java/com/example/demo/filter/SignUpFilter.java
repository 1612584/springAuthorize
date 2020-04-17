package com.example.demo.filter;

import com.example.demo.model.AccountCredentials;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpFilter implements Filter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("doFilter() method is invoked");
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String password = request.getParameter("password");
        System.out.println("run filter sign-up");
        //request.setAttribute("user",new AccountCredentials(request.getParameter("username"),passwordEncoder.encode(password)));
        chain.doFilter(request,response);
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;


    }
}
