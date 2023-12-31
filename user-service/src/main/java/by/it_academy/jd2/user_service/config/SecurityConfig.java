package by.it_academy.jd2.user_service.config;

import by.it_academy.jd2.user_service.controller.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter) throws Exception  {
        // Enable CORS and disable CSRF
        http
                .cors(withDefaults())
                        .csrf(AbstractHttpConfigurer::disable);

        // Set session management to stateless
        http
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                                httpSecuritySessionManagementConfigurer
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Set unauthorized requests exception handler
        http
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                                httpSecurityExceptionHandlingConfigurer
                    .authenticationEntryPoint(
                        (request, response, ex) -> response.setStatus(
                                HttpServletResponse.SC_UNAUTHORIZED
                        )
                    )
                    .accessDeniedHandler(
                        (request, response, ex) -> response.setStatus(
                            HttpServletResponse.SC_FORBIDDEN
                        )
                    )
                );

        // Set permissions on endpoints
        http
                .authorizeHttpRequests(requests -> requests
                // Our public endpoints
                .requestMatchers("/users/registration", "/users/verification", "/users/login")
                        .permitAll()
                //Следующие два пример делают одно и тоже
                .requestMatchers(HttpMethod.GET,"/users", "/users/{uuid}").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/users").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/users").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/users/me").authenticated()
                // Our private endpoints
                .anyRequest().authenticated()
        );

        // Add JWT token filter
        http
                .addFilterBefore(filter,
                    UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}