package com.szedavid.sightseeing.security.dev;

import com.szedavid.sightseeing.security.WebSecurityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class WebSecurityConfigurationDev extends WebSecurityConfiguration {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();  // makes testing with Postman easier, but not allowed in production mode
    }
}