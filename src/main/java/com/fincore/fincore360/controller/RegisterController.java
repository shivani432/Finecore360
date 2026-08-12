package com.fincore.fincore360.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fincore.fincore360.entity.User;
import com.fincore.fincore360.service.UserService;

/*
 * @Controller:
 * Register page आणि registration request handle करण्यासाठी.
 */
@Controller
public class RegisterController {

    private final UserService userService;

    /*
     * Constructor Injection:
     * UserService Spring automatically provide करतो.
     */
    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    /*
     * GET /register:
     * Register page browser मध्ये open करतो.
     */
    @GetMapping("/register")
    public String registerPage() {

        return "register";
    }


    /*
     * POST /register:
     * Register form मधून Name, Email आणि Password घेतो.
     */
    @PostMapping("/register")
    public String register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        /*
         * नवीन User object तयार करतो.
         */
        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        /*
         * User database मध्ये save करतो.
         */
        userService.registerUser(user);

        /*
         * Registration successful झाल्यावर
         * Login page वर redirect करतो.
         */
        return "redirect:/login";
    }
}