package br.com.postech.parking.owner.application.gateway.jpa;

import br.com.postech.parking.exception.EntityNotFoundException;
import br.com.postech.parking.owner.application.dto.OwnerDTO;
import br.com.postech.parking.owner.application.gateway.OwnerGateway;
import br.com.postech.parking.owner.application.gateway.jpa.entity.OwnerEntity;
import br.com.postech.parking.owner.application.gateway.jpa.repository.OwnerRepository;
import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.owner.domain.factory.OwnerFactory;
import br.com.postech.parking.owner.domain.valueobject.OwnerDocument;
import br.com.postech.parking.owner.domain.valueobject.OwnerEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerJpaGateway implements OwnerGateway {

    public final OwnerRepository ownerRepository;
    private final OwnerFactory ownerFactory;

    public Owner createOwner(Owner owner) {
        OwnerDTO dto = ownerFactory.createOwnerDTO(owner);
        OwnerEntity entityToSave = dto.toOwnerEntity();

        ownerRepository.save(entityToSave);

        return convertToOwnerEntity(entityToSave);
    }

    @Override
    public Owner findOwnerById(Long id) {
        OwnerEntity ownerEntity = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return convertToOwnerEntity(ownerEntity);
    }

    @Override
    public Owner findByDocument(String document) {
        OwnerEntity ownerEntity = ownerRepository.findByOwnerDocument(document);
        return convertToOwnerEntity(ownerEntity);
    }

    @Override
    public List<Owner> getAllOwners() {
        List<Owner> ownerList = ownerRepository.findAll()
                .stream()
                .map(this::convertToOwnerEntity)
                .collect(Collectors.toUnmodifiableList());
        log.info("Quantity of vehicles: {}", ownerRepository.count());
        return ownerList;
    }

    @Override
    public Owner updateOwner(Long id, Owner owner) {
        log.info("Updating owner with id: {}", id);
        OwnerEntity existOwner = ownerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Owner not found with id: " + id));

        existOwner.setFirstName(owner.getFirstName());
        existOwner.setLastName(owner.getLastName());
        existOwner.setPhoneNumber(owner.getPhoneNumber());

        ownerRepository.save(existOwner);
        log.info("Update owner: {}", existOwner);

        return convertToOwnerEntity(existOwner);
    }


    @Override
    public void deleteOwner(Long id) {
        OwnerEntity ownerExist = ownerRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Owner not found with id: " + id));
        log.info("Deleting owner with id: {}", id);
        ownerRepository.delete(ownerExist);
    }

    public Owner convertToOwnerEntity(OwnerEntity entity) {
        return new Owner(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthdate(),
                OwnerDocument.createOwnerDocumentFactory(entity.getOwnerDocument()),
                OwnerEmail.createEmailFactory(entity.getOwnerEmail()),
                entity.getPhoneNumber());
    }

}
