package com.sftwrlabs.ubc_marketplace.exception;


public class ListingNotFoundException extends RuntimeException {
    public ListingNotFoundException(String message) {
        super(message);
    }
}
