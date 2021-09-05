package com.szedavid.sightseeing.sightseeing.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        // for H2 - todo del
//        httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll()
//                .and().csrf().ignoringAntMatchers("/h2-console/**")
//                .and().headers().frameOptions().sameOrigin();
//        // ... todo del to here
//    }

}