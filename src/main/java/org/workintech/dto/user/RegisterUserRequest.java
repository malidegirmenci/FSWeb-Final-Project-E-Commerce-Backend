package org.workintech.dto.user;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(
        @NotBlank(message = "Name must not be null, empty or blank")String name,
        @NotBlank(message = "Email must not be null, empty or blank")String email,
        @NotBlank(message = "Password must not be null, empty or blank")String password,
        @NotBlank(message = "Role id must not be null, empty or blank")Long role_id) {
}
