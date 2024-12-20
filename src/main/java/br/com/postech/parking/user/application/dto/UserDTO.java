package br.com.postech.parking.user.application.dto;

import br.com.postech.parking.user.domain.valueobject.UserDocument;
import br.com.postech.parking.user.domain.valueobject.UserEmail;
import java.time.LocalDate;

public record UserDTO(
    Long id,
    String firstName,
    String LastName,
    LocalDate birthdate,
    UserDocument userDocument,
    UserEmail email,
    String phoneNumber
) {
    /***
     * TODO: criar métodos de conversão, entity, dto e domain
     */
}
