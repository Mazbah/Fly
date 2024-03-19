package com.mazbah.fly.security;

import com.mazbah.fly.dao.AuthUserDao;
import com.mazbah.fly.service.UserService;
import com.mazbah.fly.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

    public JwtRequestFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        return doFilterInternalJwt(request, response, filterChain);
    }

    protected void doFilterInternalJwt(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        JwtTokenUtil.Validator validator = null;
        String userId = null;
        AuthUserDao authUser = null;

        if (requestTokenHeader != null && !requestTokenHeader.isEmpty()) {
            validator = jwtTokenUtil.getVerifier(requestTokenHeader);
            try {
                userId = validator.userId();
                authUser = userService.getAuthUser(userId);
            } catch (ExpiredJwtException e) {
                LOGGER.warn("TOKEN_EXPIRED {}", requestTokenHeader);
                LOGGER.warn(e.getLocalizedMessage());
            } catch (Exception e) {
                LOGGER.error("TOKEN_ERROR {}", requestTokenHeader);
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        } else {
            logger.warn("Token not provided");
        }

        if (authUser != null && authUser.getToken() == requestTokenHeader
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = 

        }

    }

}
