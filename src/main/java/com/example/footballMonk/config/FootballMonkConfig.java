package com.example.footballMonk.config;

import com.example.footballMonk.dto.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class FootballMonkConfig {

    @Bean
    public Response response(){
        return new Response();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
