package com.example.onlinefoodstorage.security;

import com.example.onlinefoodstorage.enums.Role;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.   cors(AbstractHttpConfigurer::disable).
                csrf(AbstractHttpConfigurer::disable).
                sessionManagement(
                        sessionManagementConfigurer ->  sessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.exceptionHandling(handlingConfigurer ->
                handlingConfigurer.authenticationEntryPoint(((request, response, authException) ->
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage()))));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).authorizeHttpRequests(auth -> auth
                .requestMatchers("/",
                        "/admin-panel/login",
                        "/swagger-ui/**",
                        "/swagger-resources/*",
                        "/v3/api-docs/**"
                )
                .permitAll()
                .requestMatchers("/api/**").hasRole(Role.USER.name())
                .requestMatchers("/admin-panel/**").hasRole(Role.ADMIN.name())
                .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
