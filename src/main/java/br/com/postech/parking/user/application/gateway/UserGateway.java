package br.com.postech.parking.user.application.gateway;

import br.com.postech.parking.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserGateway {

    User createUser(User user);

    Optional<User> findUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
