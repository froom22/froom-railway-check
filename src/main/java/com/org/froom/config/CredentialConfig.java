package com.org.froom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class CredentialConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
        security.httpBasic().disable();
        security.cors().and().csrf().disable();
    }

    public SecurityWebFilterChain configureDev(ServerHttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/v2/api-docs").permitAll()
                .pathMatchers("/configuration/ui").permitAll()
                .pathMatchers("/swagger-resources/**").permitAll()
                .pathMatchers("/configuration/security").permitAll()
                .pathMatchers("/swagger-ui.html").permitAll()
                .pathMatchers("/swagger-ui/*").permitAll()
                .pathMatchers("/webjars/**").permitAll()
                .pathMatchers("/v2/**").permitAll()
                .and().cors()
                .and().oauth2ResourceServer()
                .jwt().and().and().build();
    }
}
