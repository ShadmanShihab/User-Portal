package com.registration.userportal.security;

import com.registration.userportal.repository.RoleRepository;
import com.registration.userportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user/home", "user/reset-pass", "/user/reset-pass/save").hasAuthority("USER")
                .antMatchers("/user/registration","/user/save", "/user/test","static/**","/css/**","/js/**", "/jquery-ui-datepicker/**", "/webfonts/**", "/*").permitAll()
                .anyRequest().authenticated();

        http.formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/login-processing").permitAll()
                .successForwardUrl("/login-success");

        http.logout().logoutUrl("/logout");

        http.exceptionHandling().accessDeniedPage("/denied");
    }
}
