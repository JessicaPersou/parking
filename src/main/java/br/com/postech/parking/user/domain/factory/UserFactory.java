package br.com.postech.parking.user.domain.factory;

import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.user.application.dto.UserDTO;
import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.user.domain.valueobject.UserDocument;
import br.com.postech.parking.user.domain.valueobject.UserEmail;
import br.com.postech.parking.vehicle.domain.Vehicle;
import java.util.Collections;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUser(UserDTO dto) {
        return new User(
                dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.birthdate(),
                UserDocument.createUserDocumentFactory(dto.userDocument()),
                UserEmail.createEmailFactory(dto.userEmail()),
                dto.phoneNumber()
        );
    }

    public UserDTO createUserDTO(User entity) {
        return new UserDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthdate(),
                entity.getUserDocument().getCpf(),
                entity.getUserEmail().getEmail(),
                entity.getPhoneNumber(),
                entity.getVehicles().stream().map(Vehicle::getId).toList(),
                entity.getTickets().stream().map(Ticket::getId).toList()
        );
    }

}
