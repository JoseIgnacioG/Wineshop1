package com.example.wine;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Not found" + id);
    }
}