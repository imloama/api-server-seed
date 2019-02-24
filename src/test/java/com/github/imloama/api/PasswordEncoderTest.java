package com.github.imloama.api;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {


    @Test
    public void encode(){
        String password = "admin";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode(password);
        System.out.println(pwd);
//        assert "$2a$10$Q4OQlkJj043v4i97dhxzDO7AFpTOGWKHugNh9euqglYb5GN8MAoZO".equals(pwd);
    }

}
