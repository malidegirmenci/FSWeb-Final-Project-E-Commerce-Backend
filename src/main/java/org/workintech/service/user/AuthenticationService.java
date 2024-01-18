package org.workintech.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.user.LoginUserResponse;
import org.workintech.entity.user.Role;
import org.workintech.entity.user.User;
import org.workintech.exceptions.EcommerceException;
import org.workintech.repository.user.RoleRepository;
import org.workintech.repository.user.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@AllArgsConstructor
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public User register(String name, String email, String password, Long roleId) {
        String encodedPassword = passwordEncoder.encode(password);
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        System.out.println(optionalRole);
        Role userRole = new Role();
        if(optionalRole.isPresent()){
            userRole = roleRepository.findByCode(optionalRole.get().getCode()).orElseThrow();
        }
        System.out.println(userRole);
        Set<Role> roles = new HashSet<>();
        User user = new User();
        roles.add(userRole);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        return userRepository.save(user);
    }
    public LoginUserResponse login(String email, String password){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,password)
            );
            String token = tokenService.generateJwtToken(authentication);
            User user = userRepository.findUserByEmail(email).orElseThrow();
            user.setToken(token);
            userRepository.save(user);
            return new LoginUserResponse(user.getEmail(), user.getName(), user.getRoles().stream().findFirst().orElseThrow().getId().toString(),token);
        }catch (AuthenticationException ex){
            throw new EcommerceException("The user could not login", HttpStatus.BAD_REQUEST);
        }
    }
}
