package com.zavialov.secureMethods.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("7788"))
                .roles("READ", "WRITE", "DELETE")
                .and()
                .withUser("user1111")
                .password(passwordEncoder().encode("1111"))
                .roles("READ", "WRITE")
                .and()
                .withUser("user2222")
                .password(passwordEncoder().encode("2222"))
                .roles("READ")
                .and()
                .withUser("user3333")
                .password(passwordEncoder().encode("3333"))
                .roles("READ", "DELETE")
                .and()
                .withUser("user4444")
                .password(passwordEncoder().encode("4444"))
                .roles("DELETE")
                .and()
                .withUser("user5555")
                .password(passwordEncoder().encode("5555"))
                .roles("WRITE");
    }
}