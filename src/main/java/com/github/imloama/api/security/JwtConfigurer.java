package com.github.imloama.api.security;


import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.github.imloama.api.config.APIProperties;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;
    private APIProperties config;

    public JwtConfigurer(JwtTokenProvider jwtTokenProvider,APIProperties config) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.config  = config;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
        customFilter.setAnnoUrls(config.getExcludeUrls());
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}