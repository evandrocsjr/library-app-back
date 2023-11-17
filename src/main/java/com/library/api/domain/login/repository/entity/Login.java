package com.library.api.domain.login.repository.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Login {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String username;

    @Column
    private String password;
}
