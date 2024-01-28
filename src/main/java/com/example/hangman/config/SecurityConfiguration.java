package com.example.hangman.config;


import com.example.hangman.repository.UserRepository;
import com.example.hangman.util.ApplicationUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, SecurityContextRepository securityContextRepository) throws Exception {
        httpSecurity.
                // defines which pages will be authorized
                        authorizeHttpRequests().
                // allow access to all static files (images, CSS, js)
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                requestMatchers("/", "/register", "/login", "/play", "/login-error").permitAll().
                // the URL-s below are available for all users - logged in and anonymous
                        anyRequest().authenticated().
                and().
                // configure login with HTML form
                        formLogin().
                loginPage("/login").
                // the names of the username, password input fields in the custom login form
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // where do we go after login
//              //use true argument if you always want to go there, otherwise go to previous page
        defaultSuccessUrl("/", true).//use true argument if you always want to go there, otherwise go to previous page
                failureForwardUrl("/login-error").and().logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).
                and().
                securityContext().
                securityContextRepository(securityContextRepository);

        return httpSecurity.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ApplicationUserDetailsService(userRepository);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }


}