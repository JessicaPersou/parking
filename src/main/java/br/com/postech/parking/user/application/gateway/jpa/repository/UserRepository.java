package br.com.postech.parking.user.application.gateway.jpa.repository;


import br.com.postech.parking.user.application.gateway.jpa.entity.UserEntity;
import br.com.postech.parking.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}

