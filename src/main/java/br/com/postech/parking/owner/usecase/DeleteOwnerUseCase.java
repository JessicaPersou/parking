package br.com.postech.parking.owner.usecase;

import br.com.postech.parking.owner.application.gateway.OwnerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteOwnerUseCase {

    private final OwnerGateway ownerGateway;

    public void deleteOwner(Long id) {
        ownerGateway.deleteOwner(id);
    }
}
