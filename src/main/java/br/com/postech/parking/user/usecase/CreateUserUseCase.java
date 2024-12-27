package br.com.postech.parking.user.usecase;

import br.com.postech.parking.exception.AgeNotPermitedException;
import br.com.postech.parking.user.application.gateway.UserGateway;
import br.com.postech.parking.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserGateway userGateway;

    public User createUserUseCase(User user) {
        log.info("Creating user: {}", user);

        if (!user.isLegalAge()) {
            throw new AgeNotPermitedException("User is not of legal age");
        }

        User savedUser = userGateway.createUser(user);
        log.info("Created user: {}", savedUser);
        return savedUser;
    }
}
