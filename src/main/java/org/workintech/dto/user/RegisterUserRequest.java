package org.workintech.dto.user;

public record RegisterUserRequest(String name, String email, String password, Long roleId) {
}
