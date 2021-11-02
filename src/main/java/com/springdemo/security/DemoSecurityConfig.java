package com.springdemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder user = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication().withUser(user.username("John").password("john1").roles("EMPLOYEE")).withUser(user.username("Tom").password("Tom1").roles("MANAGER", "EMPLOYEE")).withUser(user.username("Mary").password("Mary1").roles("ADMIN", "EMPLOYEE"));


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").hasAnyRole("MANAGER", "EMPLOYEE", "ADMIN").antMatchers("/managers").hasAnyRole("MANAGER").antMatchers("/admins").hasRole("ADMIN").and().formLogin().loginPage("/showLoginPage").loginProcessingUrl("/authenticateTheUser").permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/noaccesspage");




    }
}