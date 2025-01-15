package br.com.postech.parking.vehicle.application.gateway.jpa.entity;

import br.com.postech.parking.owner.application.gateway.jpa.entity.OwnerEntity;
import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owner;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<TicketEntity> tickets = new ArrayList<>();

}