package com.example.OAuthSecurityProject.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    private static final Logger logger =
            LoggerFactory.getLogger(SecurityConfig.class);
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        logger.debug("Configuring Spring Security");


        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/css/**",
                                "/error"
                        ).permitAll()

                        .requestMatchers("/dashboard")
                        .authenticated()

                        // Everything else requires authentication
                        .anyRequest()
                        .authenticated()
                )

                .oauth2Login(oauth -> oauth
                        .defaultSuccessUrl(
                                "/dashboard",
                                true
                        )
                )


                // Logout
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );


        logger.info(
                "Spring Security configuration completed"
        );

        return http.build();
    }
}

