package com.gameticket.app.util;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("I am in filter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println(req.getRequestURI());
        if (req.getRequestURI().equals("/games/login") || req.getRequestURI().equals("/games/signup")) {
//          line below says just continue to login controller don't filter /login!
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("token") != null && session.getAttribute("token").equals("games-app-token")) {
                System.out.println("user is in session");
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("/games/login");
            }
        }


    }
}