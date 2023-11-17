package com.library.api.domain.Authentication.controller.dto;

import com.library.api.domain.user.repository.enums.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {
}
