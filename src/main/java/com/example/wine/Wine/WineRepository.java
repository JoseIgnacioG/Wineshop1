package com.example.wine.Wine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository<Wine, Long> {
    default boolean range(int number, int min, int max) {
        return number >= min && number <= max;
    }
    default boolean range(float number, int min, int max) {
        return number >= min && number <= max;
    }
}
