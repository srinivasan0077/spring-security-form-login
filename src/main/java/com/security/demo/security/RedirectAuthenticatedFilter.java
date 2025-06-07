package com.security.demo.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;

public class RedirectAuthenticatedFilter implements Filter {

    @Override
    public void doFilter(
            jakarta.servlet.ServletRequest request,
            jakarta.servlet.ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getServletPath();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if ((path.equals("/perform_login") || path.equals("/register") || path.equals("/login.html") || path.equals("/register.html"))
                && auth != null
                && auth.isAuthenticated()) {
            res.sendRedirect("/index.html");
            return;
        }

        chain.doFilter(request, response);
    }
}

