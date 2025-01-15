package br.com.postech.parking.owner.application.gateway.jpa.entity;

import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String ownerDocument;

    @Column(nullable = false, unique = true)
    private String ownerEmail;

    private String phoneNumber;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehicleEntity> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<TicketEntity> tickets = new ArrayList<>();
}
