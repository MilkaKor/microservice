package com.courseproject.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .requestMatchers("/auth/**").permitAll();
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .requestMatchers("/api/v1/event/remove/{id}").permitAll()
//                .requestMatchers("/api/v1/event/find/{name}").permitAll()
//                .requestMatchers("/api/v1/event/all").permitAll();
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .requestMatchers("/api/v1/file/all").permitAll()
//                .requestMatchers("/api/v1/file/delete/{id}").permitAll()
//                .requestMatchers("/api/v1/file/download/").permitAll();
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        http.csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/auth/**")
//                .permitAll()
//                .requestMatchers("/api/v1/event/all")
//                .permitAll()
//                .requestMatchers("/api/v1/event/find/{id}")
//                .permitAll()
//                .requestMatchers("/api/v1/file/all")
//                .permitAll()
//                .requestMatchers("/api/v1/find/{name}")
//                .permitAll()
//                .requestMatchers("/api/v1/file/download/{name}")
//                .permitAll()
//                .requestMatchers("/api/v1/event/remove/{id}")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf()
                .disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST","PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}