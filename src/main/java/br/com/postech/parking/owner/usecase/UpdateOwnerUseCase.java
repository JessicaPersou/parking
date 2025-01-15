package br.com.postech.parking.owner.usecase;

import br.com.postech.parking.owner.application.gateway.OwnerGateway;
import br.com.postech.parking.owner.domain.Owner;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateOwnerUseCase {

    private final OwnerGateway ownerGateway;

    public Owner updateOwner(Long id, Owner owner) {
        owner.getOwnerDocument();
        owner.getOwnerEmail();
        owner.getBirthdate();
        if (id == null || owner == null) {
            log.info("Owner is invalid: {} ", owner);
            throw new EntityNotFoundException("Invalid owner id or owner");
        }
        return ownerGateway.updateOwner(id, owner);
    }

}
