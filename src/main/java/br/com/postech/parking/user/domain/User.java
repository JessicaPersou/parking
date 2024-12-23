package br.com.postech.parking.user.domain;

import br.com.postech.parking.user.domain.valueobject.UserDocument;
import br.com.postech.parking.user.domain.valueobject.UserEmail;
import br.com.postech.parking.vehicle.domain.Vehicle;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.Getter;

@Getter
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private UserDocument userDocument;
    private UserEmail userEmail;
    private String phoneNumber;
    private List<Vehicle> vehicles;

    public User(Long id, String firstName, String lastName, LocalDate birthdate, UserDocument document,
            UserEmail userEmail,
            String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.userDocument = UserDocument.createUserDocumentFactory(document.getCpf());
        this.userEmail = UserEmail.createEmailFactory(userEmail.getEmail());
        this.phoneNumber = phoneNumber;
    }

    public Long getAge() {
        return birthdate.until(LocalDate.now(), ChronoUnit.YEARS);
    }

    public boolean isLegalAge() {
        return getAge() > 18;
    }

    public boolean haveVehicle() {
        return vehicles.isEmpty();
    }
}

