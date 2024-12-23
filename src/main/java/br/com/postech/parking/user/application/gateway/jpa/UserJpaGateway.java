package br.com.postech.parking.user.application.gateway.jpa;

import br.com.postech.parking.exception.EntityNotFoundException;
import br.com.postech.parking.user.application.dto.UserDTO;
import br.com.postech.parking.user.application.gateway.UserGateway;
import br.com.postech.parking.user.application.gateway.jpa.entity.UserEntity;
import br.com.postech.parking.user.application.gateway.jpa.repository.UserRepository;
import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.user.domain.factory.UserFactory;
import br.com.postech.parking.user.domain.valueobject.UserDocument;
import br.com.postech.parking.user.domain.valueobject.UserEmail;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserJpaGateway implements UserGateway {

    public final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserJpaGateway(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public User createUser(User user) {
        UserDTO dto = userFactory.createUserDTO(user);
        UserEntity entityToSave = dto.toUserEntity();

        userRepository.save(entityToSave);

        return convertToUserEntity(entityToSave);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id).map(this::convertToUserEntity);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll()
                .stream()
                .map(this::convertToUserEntity)
                .collect(Collectors.toUnmodifiableList());
        log.info("Quantity of vehicles: {}", userRepository.count());
        return userList;
    }

    @Override
    public User updateUser(Long id, User user) {
        log.info("Updating user with id: {}", id);
        UserEntity existUser = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found with id: " + id));

        existUser.setFirstName(user.getFirstName());
        existUser.setLastName(user.getLastName());
        existUser.setPhoneNumber(user.getPhoneNumber());

        userRepository.save(existUser);
        log.info("Update user: {}", existUser);

        return convertToUserEntity(existUser);
    }


    @Override
    public void deleteUser(Long id) {
        UserEntity userExist = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found with id: " + id));
        log.info("Deleting user with id: {}", id);
        userRepository.delete(userExist);
    }

    public User convertToUserEntity(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthdate(),
                UserDocument.createUserDocumentFactory(entity.getUserDocument()),
                UserEmail.createEmailFactory(entity.getUserEmail()),
                entity.getPhoneNumber());
    }

}
