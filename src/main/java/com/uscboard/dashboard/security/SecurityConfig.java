package com.uscboard.dashboard.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configurable @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private UserDetailsService userDetailsService;

    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    //@Override
   // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     //   auth.userDetailsService(userDetailsService).passwordEncoder(//bCryptPasswordEncoder);

    //}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
               // .antMatchers("/dashboard/index").hasAuthority("user").anyRequest()
               // .authenticated().and().csrf().disable().formLogin()
               // .loginPage("/").failureUrl("/login?error=true")
               // .defaultSuccessUrl("/student/index")
              //  .usernameParameter("email")
              //  .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/access-denied");

    }
    

public void configure(WebSecurity web) throws Exception{
    web
        .ignoring()
        .antMatchers("/resources/**","/static/**","/css/**","/js/**","/images/**");
}
@Bean
PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
}