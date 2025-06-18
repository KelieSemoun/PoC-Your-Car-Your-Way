package com.openclassrooms.back.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .cors(Customizer.withDefaults())
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/ws/**").permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(form -> form
    		   .successHandler(successHandler()) 
      )
      .httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public AuthenticationSuccessHandler successHandler() {
    return (request, response, authentication) -> {
      response.setStatus(HttpServletResponse.SC_OK);
    };
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration config = new CorsConfiguration();
      config.addAllowedOrigin("http://localhost:4200");
      config.addAllowedMethod("*");
      config.addAllowedHeader("*");
      config.setAllowCredentials(true);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", config);
      return source;
  }
}
