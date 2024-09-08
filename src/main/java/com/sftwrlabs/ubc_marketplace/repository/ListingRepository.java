package com.sftwrlabs.ubc_marketplace.repository;


import com.sftwrlabs.ubc_marketplace.model.Listing;
import com.sftwrlabs.ubc_marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByCategory(String category);
    List<Listing> findByUser(User user);
}

