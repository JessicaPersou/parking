package br.com.postech.parking.owner.application.gateway;

import br.com.postech.parking.owner.domain.Owner;

import java.util.List;

public interface OwnerGateway {

    Owner createOwner(Owner owner);

    Owner findOwnerById(Long id);

    Owner findByDocument(String document);

    List<Owner> getAllOwners();

    Owner updateOwner(Long id, Owner owner);

    void deleteOwner(Long id);
}
