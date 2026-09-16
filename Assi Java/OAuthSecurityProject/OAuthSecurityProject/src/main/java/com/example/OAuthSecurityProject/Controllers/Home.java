package com.example.OAuthSecurityProject.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    private static final Logger logger =
            LoggerFactory.getLogger(Home.class);

    @GetMapping("/")
    public String home(
            Authentication authentication,
            Model model) {

        boolean authenticated =
                authentication != null
                        && authentication.isAuthenticated()
                        && !"anonymousUser".equals(
                        authentication.getPrincipal()
                );

        model.addAttribute(
                "authenticated",
                authenticated
        );

        if (authenticated) {

            logger.info(
                    "Authenticated user accessed Home: {}",
                    authentication.getName()
            );

        } else {

            logger.debug(
                    "Unauthenticated user accessed Home"
            );
        }

        return "Home";
    }
}