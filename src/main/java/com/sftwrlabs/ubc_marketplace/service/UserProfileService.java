package com.sftwrlabs.ubc_marketplace.service;


import com.sftwrlabs.ubc_marketplace.model.User;
import com.sftwrlabs.ubc_marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateProfile(String email, User updatedUser) {
        User user = getUserByEmail(email);
        user.setName(updatedUser.getName());
        user.setProfilePicture(updatedUser.getProfilePicture());
        user.setBio(updatedUser.getBio());
        return userRepository.save(user);
    }
}

