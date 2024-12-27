package br.com.postech.parking.user.application.dto;

import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.user.application.gateway.jpa.entity.UserEntity;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import java.time.LocalDate;
import java.util.List;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String userDocument,
        String userEmail,
        String phoneNumber,
        List<Long> vehicleIds,
        List<Long> ticketIds
) {

    public UserEntity toUserEntity() {
        return UserEntity.builder()
                .birthdate(this.birthdate)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phoneNumber(this.phoneNumber)
                .userDocument(this.userDocument)
                .userEmail(this.userEmail)
                .build();
    }

    public static UserDTO fromUserEntity(UserEntity entity) {
        return new UserDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthdate(),
                entity.getUserDocument(),
                entity.getUserEmail(),
                entity.getPhoneNumber(),
                entity.getVehicles().stream().map(VehicleEntity::getId).toList(), // Mapear IDs dos veículos
                entity.getTickets().stream().map(TicketEntity::getId).toList()   // Mapear IDs dos tickets
        );
    }
}
