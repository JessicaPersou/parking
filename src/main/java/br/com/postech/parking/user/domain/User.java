package br.com.postech.parking.user.domain;

import br.com.postech.parking.user.domain.valueobject.UserDocument;
import br.com.postech.parking.user.domain.valueobject.UserEmail;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private UserDocument userDocument;
    private UserEmail email;
    private String phoneNumber;

    public User(Long id, String firstName, String lastName, LocalDate birthdate, UserDocument userDocument,
            UserEmail email,
            String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.userDocument = userDocument;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /***
     * TODO: criar métodos de válidação para criação de usuário
     */
}

