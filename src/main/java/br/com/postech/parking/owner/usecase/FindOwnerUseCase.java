package br.com.postech.parking.owner.usecase;

import br.com.postech.parking.owner.application.gateway.OwnerGateway;
import br.com.postech.parking.owner.domain.Owner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindOwnerUseCase {

    private final OwnerGateway ownerGateway;

    public Owner findOwner(Long id) {
        log.info("Finding owner by ID: {}", id);
        return ownerGateway.findOwnerById(id);
    }

    public List<Owner> findAllOwners() {
        log.info("Finding all owners with vehicles");
        return ownerGateway.getAllOwners();
    }
}
