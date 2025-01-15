package br.com.postech.parking.owner.adapter.controller;

import br.com.postech.parking.owner.application.dto.OwnerDTO;
import br.com.postech.parking.owner.domain.Owner;
import br.com.postech.parking.owner.domain.factory.OwnerFactory;
import br.com.postech.parking.owner.usecase.CreateOwnerUseCase;
import br.com.postech.parking.owner.usecase.DeleteOwnerUseCase;
import br.com.postech.parking.owner.usecase.FindOwnerUseCase;
import br.com.postech.parking.owner.usecase.UpdateOwnerUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    public final OwnerFactory ownerFactory;

    private final CreateOwnerUseCase createOwnerUseCase;
    private final FindOwnerUseCase findOwnerUseCase;
    private final UpdateOwnerUseCase updateOwnerUseCase;
    private final DeleteOwnerUseCase deleteOwnerUseCase;

    @PostMapping
    public ResponseEntity<OwnerDTO> createNewOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
        Owner owner = ownerFactory.createOwner(ownerDTO);
        Owner createOwner = createOwnerUseCase.createOwnerUseCase(owner);
        OwnerDTO responseDTO = ownerFactory.createOwnerDTO(createOwner);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwner(@PathVariable Long id) {
        Owner owner = findOwnerUseCase.findOwner(id);
        OwnerDTO ownerDTO = ownerFactory.createOwnerDTO(owner);
        return ResponseEntity.ok(ownerDTO);
    }

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> getAllOwners() {
        List<Owner> owners = findOwnerUseCase.findAllOwners();
        List<OwnerDTO> ownerDTOS = owners.stream().map(ownerFactory::createOwnerDTO).toList();
        return ResponseEntity.ok(ownerDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable Long id,
                                                @Valid @RequestBody OwnerDTO ownerDTO) {
        Owner owner = updateOwnerUseCase.updateOwner(id, ownerFactory.createOwner(ownerDTO));
        OwnerDTO updateOwnerDTO = ownerFactory.createOwnerDTO(owner);
        return ResponseEntity.status(HttpStatus.OK).body(updateOwnerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OwnerDTO> deleteOwner(@PathVariable Long id) {
        deleteOwnerUseCase.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}
