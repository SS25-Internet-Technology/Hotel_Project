package ch.fhnw.pizza.data.repository;

import ch.fhnw.pizza.data.domain.ExtraService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraServiceRepository extends JpaRepository<ExtraService, Long> {
    // Additional query methods can be defined here if needed
}