package com.library.api.domain.user.controller.v1;

import com.library.api.domain.user.controller.v1.dto.UserWebDTO;
import com.library.api.domain.user.service.dto.UserDTO;
import com.library.api.domain.user.service.UserService;
import com.library.api.domain.exception.LibraryException;
import com.library.api.domain.exception.CustomerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Usuários")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Resgata os usuários da licença")
    public List<UserDTO> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Resgata usuário da licença através do id.")
    public UserDTO getById(@PathVariable("id") int id) throws
            CustomerNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo usuário.")
    public UserDTO create(@RequestBody UserWebDTO userDto) throws
            LibraryException {
        return userService.createUser(userDto);
    }
}