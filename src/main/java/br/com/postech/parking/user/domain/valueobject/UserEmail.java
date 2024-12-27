package br.com.postech.parking.user.domain.valueobject;

import br.com.postech.parking.exception.InvalidFormatException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserEmail {

    private final String email;

    private UserEmail(String email) {
        this.email = validationEmail(email);
    }

    public static UserEmail createEmailFactory(String email) {
        return new UserEmail(email);
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
        return "UserEmail{" +
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
        UserEmail userEmail = (UserEmail) o;
        return Objects.equals(email, userEmail.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
