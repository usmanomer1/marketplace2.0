package com.sftwrlabs.ubc_marketplace.controller;


import com.sftwrlabs.ubc_marketplace.model.Listing;
import com.sftwrlabs.ubc_marketplace.model.User;
import com.sftwrlabs.ubc_marketplace.service.ListingService;
import com.sftwrlabs.ubc_marketplace.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<List<Listing>> getAllListings() {
        List<Listing> listings = listingService.getAllListings();
        return ResponseEntity.ok(listings);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Listing>> getListingsByCategory(@PathVariable String category) {
        List<Listing> listings = listingService.getListingsByCategory(category);
        return ResponseEntity.ok(listings);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Listing>> getListingsByUser(Authentication authentication) {
        User user = userProfileService.getUserByEmail(authentication.getName());
        List<Listing> listings = listingService.getListingsByUser(user);
        return ResponseEntity.ok(listings);
    }

    @PostMapping
    public ResponseEntity<Listing> createListing(Authentication authentication, @RequestBody Listing listing) {
        User user = userProfileService.getUserByEmail(authentication.getName());
        listing.setUser(user);
        Listing createdListing = listingService.createListing(listing);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdListing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> updateListing(@PathVariable Long id, @RequestBody Listing updatedListing) {
        Listing listing = listingService.updateListing(id, updatedListing);
        return ResponseEntity.ok(listing);
    }
}

