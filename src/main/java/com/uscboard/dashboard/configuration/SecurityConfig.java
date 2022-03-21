package com.uscboard.dashboard.configuration;

import com.uscboard.dashboard.service.UserDetailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.authenticationProvider(authenticationProvider());
    }
    @Override
    public void configure(WebSecurity web)throws Exception{
        web
            .ignoring()
            .antMatchers("/resources/**","/static/**","/css","js","uploads");
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
            .authorizeRequests()
            .antMatchers("/forgot-password", "/reset-password").permitAll()
            .antMatchers("/student/index").authenticated()
            .antMatchers("/course/index").authenticated()
            .antMatchers("/dashboard/user/index").authenticated()
            //.antMatchers("/teacher/index").authenticated()
           // .antMatchers("/course/index").hasRole("ADMIN")
            .anyRequest().authenticated()

            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .defaultSuccessUrl("/default",true)

            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

            .and()
            .exceptionHandling()
            .accessDeniedPage("/accessDenied");


            
    }

}

