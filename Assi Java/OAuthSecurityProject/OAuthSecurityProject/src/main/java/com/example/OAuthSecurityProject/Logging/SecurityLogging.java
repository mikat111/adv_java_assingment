package com.example.OAuthSecurityProject.Logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.event.EventListener;

import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;

import org.springframework.stereotype.Component;


@Component
public class SecurityLogging {

    private static final Logger logger =
            LoggerFactory.getLogger(SecurityLogging.class);

    @EventListener
    public void successfulLogin(AuthenticationSuccessEvent event) {

        logger.info(
                "Successful login: {}",
                event.getAuthentication().getName()
        );
    }

    @EventListener
    public void logout(LogoutSuccessEvent event) {

        if (event.getAuthentication() != null) {

            logger.info(
                    "User logout: {}",
                    event.getAuthentication().getName()
            );

        } else {

            logger.info("User logout");
        }
    }
}

