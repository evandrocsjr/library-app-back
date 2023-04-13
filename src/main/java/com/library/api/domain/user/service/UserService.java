package com.library.api.domain.user.service;

import com.library.api.domain.user.service.dto.UserDTO;
import com.library.api.domain.user.repository.entity.User;
import com.library.api.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers(){
        List<User> userList = userRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return userList.stream().map(u -> mapper.map(u, UserDTO.class)).collect(Collectors.toList());
    }
}
