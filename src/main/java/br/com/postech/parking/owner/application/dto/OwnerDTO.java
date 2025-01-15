package br.com.postech.parking.owner.application.dto;

import br.com.postech.parking.owner.application.gateway.jpa.entity.OwnerEntity;
import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;

import java.time.LocalDate;
import java.util.List;

public record OwnerDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String ownerDocument,
        String ownerEmail,
        String phoneNumber,
        List<Long> vehicleIds,
        List<Long> ticketIds
) {

    public OwnerEntity toOwnerEntity() {
        return OwnerEntity.builder()
                .birthdate(this.birthdate)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phoneNumber(this.phoneNumber)
                .ownerDocument(this.ownerDocument)
                .ownerEmail(this.ownerEmail)
                .build();
    }

    public static OwnerDTO fromOwnerEntity(OwnerEntity entity) {
        return new OwnerDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthdate(),
                entity.getOwnerDocument(),
                entity.getOwnerEmail(),
                entity.getPhoneNumber(),
                entity.getVehicles().stream().map(VehicleEntity::getId).toList(),
                entity.getTickets().stream().map(TicketEntity::getId).toList()
        );
    }
}
