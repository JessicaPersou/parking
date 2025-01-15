package br.com.postech.parking.owner.domain;

import br.com.postech.parking.owner.domain.valueobject.OwnerDocument;
import br.com.postech.parking.owner.domain.valueobject.OwnerEmail;
import br.com.postech.parking.ticket.domain.Ticket;
import br.com.postech.parking.vehicle.domain.Vehicle;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Owner {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private OwnerDocument ownerDocument;
    private OwnerEmail ownerEmail;
    private String phoneNumber;
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    public Owner(Long id, String firstName, String lastName, LocalDate birthdate, OwnerDocument document,
                 OwnerEmail ownerEmail,
                 String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.ownerDocument = OwnerDocument.createOwnerDocumentFactory(document.getCpf());
        this.ownerEmail = OwnerEmail.createEmailFactory(ownerEmail.getEmail());
        this.phoneNumber = phoneNumber;
    }

    public Long getAge() {
        return birthdate.until(LocalDate.now(), ChronoUnit.YEARS);
    }

    public boolean isLegalAge() {
        return getAge() >= 18;
    }

    public boolean haveVehicle() {
        return !vehicles.isEmpty();
    }
}

