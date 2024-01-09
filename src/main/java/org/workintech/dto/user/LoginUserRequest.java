package org.workintech.dto.user;

import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(
        @NotBlank(message = "Email must not be null, empty or blank") String email,
        @NotBlank(message = "Password must not be null, empty or blank") String password) {
}
