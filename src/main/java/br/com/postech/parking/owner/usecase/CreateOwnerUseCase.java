package br.com.postech.parking.owner.usecase;

import br.com.postech.parking.exception.AgeNotPermitedException;
import br.com.postech.parking.owner.application.gateway.OwnerGateway;
import br.com.postech.parking.owner.domain.Owner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateOwnerUseCase {

    private final OwnerGateway ownerGateway;

    public Owner createOwnerUseCase(Owner owner) {
        log.info("Creating user: {}", owner);

        if (!owner.isLegalAge()) {
            throw new AgeNotPermitedException("User is not of legal age");
        }

        Owner savedOwner = ownerGateway.createOwner(owner);
        return savedOwner;
    }
}
