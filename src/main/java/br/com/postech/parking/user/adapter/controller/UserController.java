package br.com.postech.parking.user.adapter.controller;

import br.com.postech.parking.user.application.dto.UserDTO;
import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.user.domain.factory.UserFactory;
import br.com.postech.parking.user.usecase.CreateUserUseCase;
import br.com.postech.parking.user.usecase.DeleteUserUseCase;
import br.com.postech.parking.user.usecase.FindUserUseCase;
import br.com.postech.parking.user.usecase.UpdateUserUseCase;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    public final CreateUserUseCase userUseCase;
    public final UserFactory userFactory;
    private final CreateUserUseCase createUserUseCase;
    private final FindUserUseCase findUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping
    public ResponseEntity<UserDTO> createNewUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userFactory.createUser(userDTO);
        User createUser = createUserUseCase.createUserUseCase(user);
        UserDTO responseDTO = userFactory.createUserDTO(createUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = findUserUseCase.findUser(id);
        UserDTO userDTO = userFactory.createUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = findUserUseCase.findAllUsers();
        List<UserDTO> userDTOs = users.stream().map(userFactory::createUserDTO)
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(userDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO) {
        User user = updateUserUseCase.updateUser(id, userFactory.createUser(userDTO));
        UserDTO updateUserDTO = userFactory.createUserDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(updateUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        deleteUserUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
