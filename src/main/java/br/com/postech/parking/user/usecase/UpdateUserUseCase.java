package br.com.postech.parking.user.usecase;

import br.com.postech.parking.user.application.gateway.UserGateway;
import br.com.postech.parking.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserGateway userGateway;

    public User updateUser(Long id, User user) {
        user.getUserDocument();
        user.getUserEmail();
        user.getBirthdate();
        if (id == null || user == null) {
            log.info("User is invalid: {} ", user);
            throw new EntityNotFoundException("Invalid user id or user");
        }
        return userGateway.updateUser(id, user);
    }

}
