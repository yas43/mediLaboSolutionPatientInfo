package com.ykeshtdar.StartP9Monolothic.filter;

import com.ykeshtdar.StartP9Monolothic.service.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.preauth.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;
import org.springframework.web.filter.*;
import org.springframework.web.util.*;

import java.io.*;
import java.util.*;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final RestTemplate restTemplate;
    private final JwtService jwtService;
    private final CustomUserDetailService userDetailService;


    public JwtAuthFilter(RestTemplate restTemplate, JwtService jwtService, CustomUserDetailService userDetailService) {
        this.restTemplate = restTemplate;
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("inside pre filter patient request header is "+request.getHeader(HttpHeaders.AUTHORIZATION));



        if ("true".equals(request.getHeader("internalRequest"))) {

            // Create a pre-authenticated token
            PreAuthenticatedAuthenticationToken authentication =
                    new PreAuthenticatedAuthenticationToken("internalUser", null, null);

            // Set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);


            filterChain.doFilter(request, response);

            return;
        }



        String authorizationHeader = request.getHeader("Authorization");

        String token = null;
        String userName = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwtService.getUsername(token);
            System.out.println("inside patient pre filter and token is :"+token);
            System.out.println("inside patient pre filter and userName is :"+userName);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailService.loadUserByUsername(userName);
            System.out.println("inside patient pre filter and user detail is :"+userDetails);

            if (jwtService.validateToken(token)) {
                System.out.println("inside patient pre filter associate authentication");

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }



}

