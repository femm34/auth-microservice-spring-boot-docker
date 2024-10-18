package com.fecd.auth.config;

import com.fecd.auth.models.dao.UserDao;
import com.fecd.auth.services.JwtService;
import com.fecd.auth.utils.MapAuthorities;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDao userDao;

    public JwtAuthFilter(JwtService jwtService, UserDao userDao) {
        this.jwtService = jwtService;
        this.userDao = userDao;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            Optional.ofNullable(jwtService.getUserIdFromToken(jwtToken))
                    .flatMap(userDao::findById)
                    .ifPresent(user -> {
                        request.setAttribute("X-User-Id", user.getId());
                        processAuthentication(request, new User(user.getUsername(), user.getPassword(), MapAuthorities.execute(user.getRoles())));
                    });
        }
        filterChain.doFilter(request, response);
    }

    private void processAuthentication(HttpServletRequest request, UserDetails userDetails) {
        String jwtToken = request.getHeader("Authorization").substring(7);
        Optional.of(jwtToken)
                .filter(token -> !jwtService.isExpired(token))
                .ifPresent(token -> {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                });
    }
}
