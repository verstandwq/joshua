package org.gyt.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全配置
 * Created by y27chen on 2016/7/12.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/index",
                        "/home",
                        "/bible",
                        "/believe",
                        "/contact",
                        "/fellowship",
                        "/financial",
                        "/group",
                        "/about",
                        "/wedding",
                        "/media",
                        "/newcomer",
                        "/pastor",
                        "/recommend",
                        "/report",
                        "/service",
                        "/sunday",
                        "/testimony",
                        "/worship",
                        "/public",
                        "/suffrage",
                        "/login",
                        "/logon",
                        "/forget",
                        "/assets/**",
                        "/images/**"
                ).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN_ACCESS')")
                .antMatchers("/admin/user/**").access("hasRole('ROLE_MANAGE_USER_STATUS')")
                .antMatchers("/api/**").access("hasRole('ROLE_ADMIN_ACCESS')")
                .antMatchers("/message/publish").access("hasRole('ROLE_SEND_MESSAGE')")
                .antMatchers("/message/read").access("hasRole('ROLE_MANAGE_MESSAGE')")
                .antMatchers("/message/unread").access("hasRole('ROLE_MANAGE_MESSAGE')")
                .antMatchers("/info").access("hasRole('ROLE_SEND_MESSAGE')")
                .antMatchers("/password").access("hasRole('ROLE_SEND_MESSAGE')")
                .and()
                .csrf()
                .ignoringAntMatchers("/forget", "/login", "/logon")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .and()
                .logout()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }
}
