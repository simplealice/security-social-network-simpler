package com.socialnet.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.
        csrf().
        disable().
        authorizeHttpRequests().
        requestMatchers("/auth/**").permitAll().
//        requestMatchers("/coaches/**").permitAll().
//        requestMatchers("/competitions/**").permitAll().
//        requestMatchers("/events/**").permitAll().
//        requestMatchers("/photos/**").permitAll().
        requestMatchers("/news/**").permitAll().
        anyRequest().authenticated().
        and().sessionManagement().
        sessionCreationPolicy(SessionCreationPolicy.STATELESS).
        and().authenticationProvider(authenticationProvider).
        addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
