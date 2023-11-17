package com.library.api.domain.Authentication.controller;

import com.library.api.domain.Authentication.controller.dto.AuthenticationDTO;
import com.library.api.domain.Authentication.controller.dto.RegisterDTO;
import com.library.api.domain.user.repository.UserRepository;
import com.library.api.domain.user.repository.entity.User;
import com.library.api.domain.user.repository.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
        if (this.userRepository.findByUsername(registerDTO.username()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());

        User newUser = User
                .builder()
                .username(registerDTO.username())
                .password(encryptedPassword)
                .role(registerDTO.userRole())
                .build();

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
