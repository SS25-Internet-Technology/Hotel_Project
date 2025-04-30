package ch.fhnw.pizza.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    // Example query method (if fullName exists in Guest entity)
    Guest findByFullName(String fullName);

    // Add more methods as needed based on entity fields
}
