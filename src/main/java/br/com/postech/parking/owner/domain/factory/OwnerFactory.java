package br.com.postech.parking.owner.domain.factory;

import br.com.postech.parking.owner.application.dto.OwnerDTO;
import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.owner.domain.valueobject.OwnerDocument;
import br.com.postech.parking.owner.domain.valueobject.OwnerEmail;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.vehicle.domain.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class OwnerFactory {

    public Owner createOwner(OwnerDTO dto) {
        return new Owner(
                dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.birthdate(),
                OwnerDocument.createOwnerDocumentFactory(dto.ownerDocument()),
                OwnerEmail.createEmailFactory(dto.ownerEmail()),
                dto.phoneNumber()
        );
    }

    public OwnerDTO createOwnerDTO(Owner owner) {
        return new OwnerDTO(
                owner.getId(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getBirthdate(),
                owner.getOwnerDocument().getCpf(),
                owner.getOwnerEmail().getEmail(),
                owner.getPhoneNumber(),
                owner.getVehicles().stream().map(Vehicle::getId).toList(),
                owner.getTickets().stream().map(Ticket::getId).toList()
        );
    }

}
