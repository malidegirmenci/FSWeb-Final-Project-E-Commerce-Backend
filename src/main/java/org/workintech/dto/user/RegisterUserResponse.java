package org.workintech.dto.user;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserResponse(@NotBlank(message = "Message must not be null, empty or blank")String message) {
}
