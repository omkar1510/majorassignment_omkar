package com.example.socialmedia.SocialMediaApplication.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Check if user exists
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
        }

        // Check if password is correct
        if (!userService.isPasswordCorrect(user, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/Password Incorrect");
        }

        return ResponseEntity.ok("Login Successful");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        String email = signupRequest.getEmail();

        // Check if user already exists
        if (userService.isUserExists(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden, Account already exists");
        }

        // Create new user
        User newUser = new User();
        newUser.setEmail(signupRequest.getEmail());
        newUser.setName(signupRequest.getName());
        newUser.setPassword(signupRequest.getPassword());

        userService.createUser(newUser);

        return ResponseEntity.ok("Account Creation Successful");
    }
}
