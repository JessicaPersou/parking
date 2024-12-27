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

    public UserDTO createUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getUserDocument().getCpf(),
                user.getUserEmail().getEmail(),
                user.getPhoneNumber(),
                user.getVehicles().stream().map(Vehicle::getId).toList(),
                user.getTickets().stream().map(Ticket::getId).toList()
        );
    }

}
