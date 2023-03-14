package com.flamingo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// @EnableGlobalMethodSecurity(
//         securedEnabled = true,
//         jsr250Enabled = true,
//         prePostEnabled = true
// )
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

        public static final String[] PUBLIC_URLS = {"/api/register/*", "/api/login","/api/categories/**","/api/products/**" };
        public static final String[] USER_URLS = { "/api/public/**" };
        public static final String[] ADMIN_URLS = { "/api/admin/**" };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable() //disable some kind of verification
                .authorizeHttpRequests()
                .requestMatchers(PUBLIC_URLS ).permitAll()
				.requestMatchers(USER_URLS).hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
				.requestMatchers(ADMIN_URLS).hasAuthority("ROLE_ADMIN")
				.anyRequest() //any other requests must be auth
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //let spring create new session for every request
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //add jwt filter before username pass auth filter
//        http.formLogin();
        return http.build();
    }

}