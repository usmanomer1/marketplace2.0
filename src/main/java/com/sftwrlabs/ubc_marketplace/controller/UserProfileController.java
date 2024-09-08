package com.sftwrlabs.ubc_marketplace.controller;


import com.sftwrlabs.ubc_marketplace.model.User;
import com.sftwrlabs.ubc_marketplace.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<User> getUserProfile(Authentication authentication) {
        User user = userProfileService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUserProfile(Authentication authentication, @RequestBody User updatedUser) {
        User user = userProfileService.updateProfile(authentication.getName(), updatedUser);
        return ResponseEntity.ok(user);
    }
}

