package com.github.imloama.api.security;

import com.github.imloama.api.config.jwt.JWT;
import com.github.imloama.api.config.jwt.JWTUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class JwtTokenProvider {

    @Autowired
    private UserDetailsService userDetailsService;



    public Authentication getAuthentication(String token) throws Exception {
        return getAuthentication(getJWTUser(token));
    }

    public Authentication getAuthentication(JWTUser user) throws Exception {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getName());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public JWTUser getJWTUser(String token)throws Exception{
        return JWT.getJWTUser(token);
    }
    public JWTUser getJWTUser(HttpServletRequest request)throws Exception{
        return JWT.getJWTUser(request);
    }

    public String getUsername(String token) throws Exception {
        return this.getJWTUser(token).getName();
    }


}