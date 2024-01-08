package org.workintech.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.user.LoginUserRequest;
import org.workintech.dto.user.LoginUserResponse;
import org.workintech.entity.user.Role;
import org.workintech.entity.user.User;
import org.workintech.exceptions.EcommerceException;
import org.workintech.service.user.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public LoginUserResponse login(@RequestBody LoginUserRequest loginUserRequest) {
        String username = loginUserRequest.email();
        String password = loginUserRequest.password();
        try {
            UserDetails userDetails = userService.loadUserByUsername(username);
            User user = userService.findByEmail(username);
            Role userRole = user.getRoles().stream().findFirst().get();
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                return DtoConverter.convertToLoginUserResponse(user,userRole.getId().toString());
            }
        } catch (AuthenticationException e) {
            throw new EcommerceException("The user could not login",HttpStatus.UNAUTHORIZED);
        }
        throw new EcommerceException("The user could not login",HttpStatus.UNAUTHORIZED);
    }
}
