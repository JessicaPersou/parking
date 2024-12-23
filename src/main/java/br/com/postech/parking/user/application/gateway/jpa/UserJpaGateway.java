package br.com.postech.parking.user.application.gateway.jpa;

import br.com.postech.parking.user.application.dto.UserDTO;
import br.com.postech.parking.user.application.gateway.UserGateway;
import br.com.postech.parking.user.application.gateway.jpa.entity.UserEntity;
import br.com.postech.parking.user.application.gateway.jpa.repository.UserRepository;
import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.user.domain.factory.UserFactory;
import br.com.postech.parking.user.domain.valueobject.UserDocument;
import br.com.postech.parking.user.domain.valueobject.UserEmail;
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

    public User createUser(User user){

        UserDTO dto = userFactory.createUserDTO(user);
        UserEntity entityToSave = dto.toUserEntity();

        userRepository.save(entityToSave);

        return convertToUserEntity(entityToSave);
    }

    public User convertToUserEntity(UserEntity entity){
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
