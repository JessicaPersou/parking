package br.com.postech.parking.owner.application.gateway;

import br.com.postech.parking.owner.domain.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerGateway {

    Owner createOwner(Owner owner);

    Owner findOwnerById(Long id);

    List<Owner> getAllOwners();

    Owner updateOwner(Long id, Owner owner);

    void deleteOwner(Long id);
}
