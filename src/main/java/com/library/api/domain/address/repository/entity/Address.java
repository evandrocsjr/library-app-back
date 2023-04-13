package com.library.api.domain.address.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column
    private String street;

    @Column
    private String state;

    @Column
    private String country;

    @Column
    private String district;
}
