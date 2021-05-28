package com.example.graphqlsample.demo.auth

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
                .mvcMatchers("/health").permitAll()
                .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .addFilter(JwtAuthorizationFilter(authenticationManager()))
    }
}


