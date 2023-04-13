package com.library.api.controller;

import com.library.api.domain.user.service.dto.UserDTO;
import com.library.api.domain.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v2/users")
@CrossOrigin
@RequiredArgsConstructor
@Api(tags = "Usuários")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAll(){
        return userService.getAllUsers();
    }

}
