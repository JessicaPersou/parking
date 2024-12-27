package br.com.postech.parking.user.usecase;

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

//        if(!user.hasVehicle()){
//            throw new RuntimeException("User haven`t vehicle");
//        }

        if (!user.isLegalAge()) {
            throw new RuntimeException("User isan`t legal age");
        }

        userGateway.createUser(user);
        log.info("Created user: {}", user);
        return user;
    }
}
