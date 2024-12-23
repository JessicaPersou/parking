package br.com.postech.parking.user.usecase;

import br.com.postech.parking.user.application.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserGateway userGateway;

    public void deleteUser(Long id) {
        userGateway.deleteUser(id);
    }
}
