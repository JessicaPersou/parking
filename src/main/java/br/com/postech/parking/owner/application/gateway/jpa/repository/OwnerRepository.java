package br.com.postech.parking.owner.application.gateway.jpa.repository;


import br.com.postech.parking.owner.application.gateway.jpa.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {

}

