package org.workintech.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.user.RegisterUserRequest;
import org.workintech.dto.user.RegisterUserResponse;
import org.workintech.service.user.AuthenticationService;
@AllArgsConstructor
@RestController
@RequestMapping("/signup")
public class RegisterController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public RegisterUserResponse register(@RequestBody RegisterUserRequest registerUserRequest){
        authenticationService
                .register(registerUserRequest.name(), registerUserRequest.email(), registerUserRequest.password(), registerUserRequest.role_id());
        return DtoConverter.convertToRegisterUserResponse("The user has been registered");
    }
}
