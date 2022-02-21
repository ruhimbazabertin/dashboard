package com.uscboard.dashboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configurable
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http
        //         .csrf().disable()
        //         .authorizeRequests()
        //         .antMatchers("/login").permitAll()
        //         .antMatchers("/dashboard").hasAuthority("USER")
        //         .antMatchers("/").permitAll()
        //         .and().formLogin();
                //.authenticated().and().csrf().disable().formLogin()
                //.loginPage("/").failureUrl("/login?error=true")
                //.defaultSuccessUrl("/student/index")
              //  .usernameParameter("email")
              //  .passwordParameter("password")
                // .and().logout()
                // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // .logoutSuccessUrl("/login").and().exceptionHandling()
                // .accessDeniedPage("/access-denied");

    }
    

public void configure(WebSecurity web) throws Exception{
    web
        .ignoring()
        .antMatchers("/resources/**","/static/**","/css/**","/js/**","/images/**");
}
}