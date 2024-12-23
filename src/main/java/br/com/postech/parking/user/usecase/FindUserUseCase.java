package br.com.postech.parking.user.usecase;

import br.com.postech.parking.user.application.gateway.UserGateway;
import br.com.postech.parking.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindUserUseCase {

    private final UserGateway userGateway;

    public User findUser(Long id) {
        log.info("Finding user by ID: {}", id);
        return userGateway.findUserById(id).orElseThrow(() -> new EntityNotFoundException("Document not found: " + id));
    }

    public List<User> findAllUsers() {
        log.info("Finding all users with vehicles");
        return userGateway.getAllUsers();
    }
}
