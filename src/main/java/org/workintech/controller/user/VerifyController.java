package org.workintech.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.workintech.dto.user.LoginUserResponse;
import org.workintech.service.user.UserService;


@AllArgsConstructor
@RestController
@RequestMapping("/verify")
public class VerifyController {
    private UserService userService;
    @GetMapping("/{token}")
    public LoginUserResponse verify(@PathVariable String token ) {
        return userService.findByToken(token);
    }
}
