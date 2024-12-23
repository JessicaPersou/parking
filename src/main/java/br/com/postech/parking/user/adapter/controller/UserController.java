package br.com.postech.parking.user.adapter.controller;

import br.com.postech.parking.user.application.dto.UserDTO;
import br.com.postech.parking.user.domain.User;
import br.com.postech.parking.user.domain.factory.UserFactory;
import br.com.postech.parking.user.usecase.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    public ResponseEntity<UserDTO> createNewUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userFactory.createUser(userDTO);
        User createUser = createUserUseCase.createUserUseCase(user);
        UserDTO responseDTO = userFactory.createUserDTO(createUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
