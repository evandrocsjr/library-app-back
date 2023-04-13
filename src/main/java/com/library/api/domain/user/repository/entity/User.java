package com.library.api.domain.user.repository.entity;

import com.library.api.domain.address.repository.entity.Address;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String telephone;

    @Column
    private String email;

    @Column
    private String password;

    @OneToOne
    private Address address;
}
