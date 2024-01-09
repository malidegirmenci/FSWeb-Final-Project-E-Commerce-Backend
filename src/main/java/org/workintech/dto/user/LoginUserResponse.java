package org.workintech.dto.user;

import jakarta.validation.constraints.NotBlank;

public record LoginUserResponse(
        @NotBlank(message = "Email must not be null, empty or blank")String email,
        @NotBlank(message = "Name must not be null, empty or blank")String name,
        @NotBlank(message = "Role id must not be null, empty or blank")String role_id,
        @NotBlank(message = "Token must not be null, empty or blank")String token) {
}
