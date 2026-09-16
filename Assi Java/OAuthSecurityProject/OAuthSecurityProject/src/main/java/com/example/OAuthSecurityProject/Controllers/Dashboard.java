package com.example.OAuthSecurityProject.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Dashboard {

    private static final Logger logger =
            LoggerFactory.getLogger(Dashboard.class);

    @GetMapping("/dashboard")
    public String dashboard(
            Authentication authentication,
            Model model) {

        logger.info(
                "Protected endpoint /dashboard accessed"
        );

        OAuth2User user =
                (OAuth2User) authentication.getPrincipal();

        String name =
                user.getAttribute("name");

        String email =
                user.getAttribute("email");

        logger.info(
                "Dashboard opened by user: {}",
                email
        );

        model.addAttribute(
                "name",
                name
        );

        model.addAttribute(
                "email",
                email
        );

        model.addAttribute(
                "authenticated",
                authentication.isAuthenticated()
        );

        return "Dashboard";
    }
}