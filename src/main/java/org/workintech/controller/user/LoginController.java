package org.workintech.controller.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workintech.dto.user.LoginUserRequest;
import org.workintech.dto.user.LoginUserResponse;
import org.workintech.service.user.AuthenticationService;


@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    private AuthenticationService authenticationService;

    @PostMapping
    public LoginUserResponse login(@Valid @RequestBody LoginUserRequest loginUserRequest) {
       return authenticationService.login(loginUserRequest.email(), loginUserRequest.password());
    }
}
