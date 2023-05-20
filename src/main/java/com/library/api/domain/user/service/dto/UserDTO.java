package com.library.api.domain.user.service.dto;

import com.library.api.domain.address.repository.entity.Address;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private Address address;
}
