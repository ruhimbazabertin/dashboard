package com.uscboard.dashboard.security;

import com.uscboard.dashboard.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    private final UserRepository userRepo; 

    public SecurityConfig(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(username -> userRepo
            .findByUsername(username)
            .orElseThrow(
                ()-> new UsernameNotFoundException(
                    format("User: %s, not found", username)
                )
            ));       
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.antMatcher("/**").authorizeRequests().anyRequest().hasRole("user")
                                .and().formLogin().loginPage("/index.html")
                                .failureUrl("/index.html?error=1").loginProcessingUrl("/index")
                                .permitAll().and().logout()
                                .logoutUrl("/student/index");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
