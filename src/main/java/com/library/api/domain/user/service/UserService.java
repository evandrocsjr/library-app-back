package com.library.api.domain.user.service;

import com.library.api.domain.user.controller.v1.dto.UserWebDTO;
import com.library.api.domain.user.service.dto.UserDTO;
import com.library.api.domain.user.repository.entity.User;
import com.library.api.domain.user.repository.UserRepository;
import com.library.api.exception.LibraryException;
import com.library.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final String USER_NOT_EXISTS = "Usuário não encontrado.";

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return userList.stream().map(u -> mapper.map(u, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO getUserById(long id) throws NotFoundException {
        ModelMapper mapper = new ModelMapper();
        Optional<User> userDb = userRepository.findById(id);
        if(userDb.isEmpty()) {
            throw new NotFoundException(USER_NOT_EXISTS);
        }

        return mapper.map(userDb, UserDTO.class);
    }

    public UserDTO createUser(UserWebDTO newUser) throws LibraryException {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(newUser, User.class);
        validateUser(user);
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    private void validateUser(User user) throws LibraryException {
        if(user.getName().isBlank()) {
            throw new LibraryException("Obrigatório inserir nome no usuário.");
        }
        if(user.getUsername().isBlank()) {
            throw new LibraryException("Obrigatório inserir email no usuário.");
        }
        if(user.getPassword().isBlank()) {
            throw new LibraryException("Obrigatório inserir senha no usuário.");
        }
    }
}
