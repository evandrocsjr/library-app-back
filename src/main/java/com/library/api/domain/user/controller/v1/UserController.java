package com.library.api.domain.user.controller.v1;

import com.library.api.domain.user.controller.v1.dto.UserWebDTO;
import com.library.api.domain.user.service.dto.UserDTO;
import com.library.api.domain.user.service.UserService;
import com.library.api.exception.LibraryException;
import com.library.api.exception.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@CrossOrigin
@RequiredArgsConstructor
@Api(tags = "Usuários")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Resgata os usuários da licença")
    public List<UserDTO> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Resgata usuário da licença através do id.")
    public UserDTO getById(@PathVariable("id") int id) throws
            NotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um novo usuário.")
    public UserDTO create(@RequestBody UserWebDTO userDto) throws
            LibraryException {
        return userService.createUser(userDto);
    }
}