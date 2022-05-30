package com.example.wine.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Lazy
@Configuration
@EnableWebSecurity
public class BasicAuthConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("USER");
//    }

    private final PasswordEncoder pwEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();
    @Bean
    UserDetailsService authentication() {
        UserDetails peter = User.builder()
                .username("leo")
                .password(pwEncoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails jodie = User.builder()
                .username("jodie")
                .password(pwEncoder.encode("password"))
                .roles("USER", "ADMIN")
                .build();
        System.out.println("   >>> Leo's password: " + peter.getPassword());
        System.out.println("   >>> Jodie's password: " + jodie.getPassword());
        return new InMemoryUserDetailsManager(peter, jodie);
    }
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
//                .mvcMatchers("/wine/**").hasRole("ADMIN")
                .antMatchers("/swagger-ui/**", "/javainuse-openapi/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
}
