package com.github.imloama.api.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.github.imloama.api.config.jwt.JWTUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;
    private List<String> annoUrls;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public List<String> getAnnoUrls() {
		return annoUrls;
	}
    public void setAnnoUrls(List<String> annoUrls) {
		this.annoUrls = annoUrls;
	}
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        try{
        	String path = ((HttpServletRequest)req).getRequestURI();
        	if(this.annoUrls!= null && this.annoUrls.contains(path)) {
        		filterChain.doFilter(req, res);
        		return;
        	}
        	System.out.println("xxxxxxxxxxxxxxxxx--"+((HttpServletRequest)req).getRequestURI());
            JWTUser user = jwtTokenProvider.getJWTUser((HttpServletRequest) req);
            if (user != null ) {
                Authentication auth = jwtTokenProvider.getAuthentication(user);
                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }catch (Exception e){
//            throw new IOException(e.getMessage(),e);
            log.error(e.getMessage(),e);
        }

        filterChain.doFilter(req, res);
    }

}