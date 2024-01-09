package org.workintech.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workintech.service.user.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping("/{id}")
    public String remove(@PathVariable Long id){
        userService.delete(id);
        return "User deleted";
    }
}
