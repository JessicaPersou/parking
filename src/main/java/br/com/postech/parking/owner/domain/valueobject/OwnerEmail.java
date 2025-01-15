package br.com.postech.parking.owner.domain.valueobject;

import br.com.postech.parking.exception.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class OwnerEmail {

    private final String email;

    private OwnerEmail(String email) {
        this.email = validationEmail(email);
    }

    public static OwnerEmail createEmailFactory(String email) {
        return new OwnerEmail(email);
    }

    private String validationEmail(String email) {
        log.info("Validating Email: {}", email);
        String emailValidator = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (!email.matches(emailValidator)) {
            log.info("Invalid email format: {}", email);
            throw new InvalidFormatException("Invalid email format.");
        }
        return email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "OwnerEmail{" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OwnerEmail ownerEmail = (OwnerEmail) o;
        return Objects.equals(email, ownerEmail.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
