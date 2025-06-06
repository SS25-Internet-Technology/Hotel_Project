package ch.fhnw.hotel.data.repository;

import ch.fhnw.hotel.data.domain.PaymentInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {
    // Additional query methods can be defined here if needed
}