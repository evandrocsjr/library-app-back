package com.library.api.testUtil;

import com.library.api.domain.user.repository.UserRepository;
import com.library.api.domain.user.repository.entity.User;
import com.library.api.domain.user.repository.enums.UserRole;
import lombok.RequiredArgsConstructor;

public class UserTestUtil {

    public static User createAdminUser(UserRepository userRepository) {
        User newUser = User
            .builder()
            .role(UserRole.ADMIN)
            .password("123456789")
            .email("evandro@gmail.com")
            .name("evandro")
            .build();


        return userRepository.save(newUser);
    }
}
