package com.example.election.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Component
public class Interceptor implements HandlerInterceptor {
    private static final List<String> PUBLIC_ENDPOINTS = Arrays.asList("/signin", "/signup");
    private static final Logger logger = Logger.getLogger(Interceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        if (PUBLIC_ENDPOINTS.contains(requestURI)) {
            return true;
        }

        String userId = request.getHeader("userId");

        if (userId == null || userId.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Unauthorized access to the resource.");
            return false;
        }
        logger.info("Authorised access for userId: " + userId);
        return true;
    }
}
