package com.library.api.domain.Authentication.controller;

import com.library.api.domain.Authentication.controller.dto.AuthenticationDTO;
import com.library.api.domain.Authentication.controller.dto.LoginResponseDTO;
import com.library.api.domain.Authentication.controller.dto.RegisterDTO;
import com.library.api.domain.security.TokenService;
import com.library.api.domain.user.repository.UserRepository;
import com.library.api.domain.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
        if (this.userRepository.findByEmail(registerDTO.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());

        User newUser = User
                .builder()
                .email(registerDTO.email())
                .password(encryptedPassword)
                .role(registerDTO.role())
                .build();

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
