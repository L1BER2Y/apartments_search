package by.shershen.user_service.config;

import by.shershen.user_service.controller.filter.JwtFilter;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

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
                )

                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/users/registration").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/verification").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/me").authenticated()
                        .requestMatchers(HttpMethod.GET, "/users").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/{uuid}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )


                .addFilterBefore(filter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}