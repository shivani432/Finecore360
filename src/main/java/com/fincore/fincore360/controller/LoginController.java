package com.fincore.fincore360.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fincore.fincore360.entity.User;
import com.fincore.fincore360.service.UserService;

import jakarta.servlet.http.HttpSession;
/*
 * @Controller:
 * Login form मधून आलेली request handle करण्यासाठी.
 */
@Controller
public class LoginController {

    private final UserService userService;

    /*
     * Constructor Injection:
     * UserService Spring automatically provide करतो.
     */
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /*
     * POST /login:
     * login.html मधून email आणि password घेतो.
     */
    @PostMapping("/login")
   public String login(
        @RequestParam String email,
        @RequestParam String password,
        HttpSession session) {

        /*
         * Email आणि password वापरून User login check करतो.
         */
        User user = userService.loginUser(email, password);

        /*
         * Login successful असेल तर
         * Main Dashboard वर redirect करतो.
         */
        if (user != null) {

    // Logged-in User session मध्ये store करतो.
    session.setAttribute("user", user);

    // Login successful झाल्यावर Dashboard वर redirect करतो.
    return "redirect:/";
}

        /*
         * Login details चुकीच्या असतील तर
         * Login page वर error parameter सोबत परत जातो.
         */
        return "redirect:/login?error=true";
    }
}
