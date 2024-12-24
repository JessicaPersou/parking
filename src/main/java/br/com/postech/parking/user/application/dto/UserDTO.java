package br.com.postech.parking.user.application.dto;

import br.com.postech.parking.user.application.gateway.jpa.entity.UserEntity;
import java.time.LocalDate;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String userDocument,
        String userEmail,
        String phoneNumber
) {

    public UserEntity toUserEntity() {
        return UserEntity.builder()
                .birthdate(this.birthdate)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phoneNumber(this.phoneNumber)
                .userDocument(this.userDocument)
                .userEmail(this.userEmail)
                .build();
    }
}
