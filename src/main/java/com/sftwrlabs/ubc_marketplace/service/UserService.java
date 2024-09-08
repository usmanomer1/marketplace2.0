package com.sftwrlabs.ubc_marketplace.service;



import com.sftwrlabs.ubc_marketplace.dto.UserRegistrationDto;
import com.sftwrlabs.ubc_marketplace.model.User;
import com.sftwrlabs.ubc_marketplace.repository.UserRepository;
import com.sftwrlabs.ubc_marketplace.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public User registerUser(UserRegistrationDto registrationDto) {
        if (!registrationDto.isValidUBCEmail()) {
            throw new IllegalArgumentException("Email must be a valid UBC student email (name@student.ubc.ca)");
        }

        User user = new User();
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setProfilePicture(null);
        user.setBio(null);
        user = userRepository.save(user);

        // Generate verification token and URL
        String token = generateVerificationToken(user); // Implement token generation logic
        String verificationUrl = "http://your-domain.com/verify-email?token=" + token;

        // Send verification email
        emailService.sendVerificationEmail(user.getEmail(), user.getName(), verificationUrl);

        return user;
    }

    public boolean verifyEmailToken(String token) {
        // Implement email verification logic based on the token
        return true; // Placeholder
    }

    public User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private String generateVerificationToken(User user) {
        // Implement token generation logic (e.g., JWT or UUID)
        return "generated-token";
    }
}


