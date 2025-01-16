package br.com.postech.parking.ticket.application.gateway.jpa;

import br.com.postech.parking.exception.EntityNotFoundException;
import br.com.postech.parking.owner.application.gateway.jpa.entity.OwnerEntity;
import br.com.postech.parking.owner.application.gateway.jpa.repository.OwnerRepository;
import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.owner.domain.valueobject.OwnerDocument;
import br.com.postech.parking.owner.domain.valueobject.OwnerEmail;
import br.com.postech.parking.ticket.application.gateway.TicketGateway;
import br.com.postech.parking.ticket.application.gateway.jpa.entity.TicketEntity;
import br.com.postech.parking.ticket.application.gateway.jpa.repository.TicketRepository;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.ticket.domain.factory.TicketFactory;
import br.com.postech.parking.vehicle.application.gateway.jpa.entity.VehicleEntity;
import br.com.postech.parking.vehicle.application.gateway.jpa.repository.VehicleRepository;
import br.com.postech.parking.vehicle.domain.Vehicle;
import br.com.postech.parking.vehicle.domain.valueobject.VehiclePlate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketJpaGateway implements TicketGateway {

    private final TicketRepository ticketRepository;
    private final TicketFactory ticketFactory;
    private final VehicleRepository vehicleRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public Ticket generateTicket(Ticket ticket) {
        VehicleEntity vehicleEntity = vehicleRepository.findById(ticket.getVehicle().getId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        OwnerEntity ownerEntity = ownerRepository.findById(ticket.getOwner().getId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));

        TicketEntity entity = new TicketEntity();
        entity.setEntryTime(ticket.getEntryTime());
        entity.setExitTime(ticket.getExitTime());
        entity.setStatus(ticket.getStatus());
        entity.setTotalAmount(ticket.getTotalAmount());
        entity.setVehicle(vehicleEntity);
        entity.setOwner(ownerEntity);

        TicketEntity saved = ticketRepository.save(entity);
        return convertToUserEntity(saved);
    }

    public Ticket convertToUserEntity(TicketEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        Ticket ticket = new Ticket();

        Vehicle vehicle = new Vehicle(
                entity.getVehicle().getId(),
                VehiclePlate.createVehiclePlateFactory(entity.getVehicle().getPlate()),
                entity.getVehicle().getModel(),
                entity.getVehicle().getColor()
        );
        ticket.setVehicle(vehicle);


        Owner owner = new Owner(
                entity.getOwner().getId(),
                entity.getOwner().getFirstName(),
                entity.getOwner().getLastName(),
                entity.getOwner().getBirthdate(),
                OwnerDocument.createOwnerDocumentFactory(entity.getOwner().getOwnerDocument()),
                OwnerEmail.createEmailFactory(entity.getOwner().getOwnerEmail()),
                entity.getOwner().getPhoneNumber()
        );
        ticket.setOwner(owner);

        ticketFactory.createTicket(vehicle, owner);
        return ticket;
    }
}
