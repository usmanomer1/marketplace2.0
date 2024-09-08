package com.sftwrlabs.ubc_marketplace.service;



import com.sftwrlabs.ubc_marketplace.exception.ListingNotFoundException;
import com.sftwrlabs.ubc_marketplace.model.Listing;
import com.sftwrlabs.ubc_marketplace.model.User;
import com.sftwrlabs.ubc_marketplace.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public Listing updateListing(Long id, Listing updatedListing) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ListingNotFoundException("Listing not found"));
        listing.setTitle(updatedListing.getTitle());
        listing.setDescription(updatedListing.getDescription());
        listing.setPrice(updatedListing.getPrice());
        listing.setCategory(updatedListing.getCategory());
        listing.setImageUrl(updatedListing.getImageUrl());
        return listingRepository.save(listing);
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public List<Listing> getListingsByCategory(String category) {
        return listingRepository.findByCategory(category);
    }

    public List<Listing> getListingsByUser(User user) {
        return listingRepository.findByUser(user);
    }
}

