package ch.fhnw.hotel.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@Table(name = "payment_info")
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden // Oculta el campo id en la documentación Swagger
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE) // No setter for id, as it is a unique identifier
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String creditCard;

    @OneToOne(mappedBy = "paymentInfo")
    @JsonIgnore
    private Reservation reservation;
}