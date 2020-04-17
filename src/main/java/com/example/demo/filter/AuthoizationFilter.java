package com.example.demo.filter;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.service.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthoizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username = TokenAuthenticationService.getUsernameFromRequest((HttpServletRequest) request);
        if(username!=null) {
            //khong co quyen
            if(username.equals("tamkg3")){
                ErrorResponse errorResponse = new ErrorResponse(401,"Unauthorized Access");

                byte[] responseToSend = restResponseBytes(errorResponse);
                ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
                ((HttpServletResponse) response).setStatus(errorResponse.getCode());
                response.getOutputStream().write(responseToSend);
                return;
            }

        }
        chain.doFilter(request, response);
    }
    private byte[] restResponseBytes(ErrorResponse eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }
//    public void setUnauthorizedResponse(HttpServletResponse response) {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json");
//        Response unAuthorizedResponse = Response.unauthorized().build();
//        try {
//            PrintWriter out = response.getWriter();
//            out.println(unAuthorizedResponse.toJsonString());
//        } catch (IOException e) {
//            log.error("Error", e);
//        }
//    }
}
