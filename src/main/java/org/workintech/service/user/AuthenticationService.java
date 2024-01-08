package org.workintech.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.workintech.entity.user.Role;
import org.workintech.entity.user.User;
import org.workintech.repository.user.RoleRepository;
import org.workintech.repository.user.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String name, String email, String password, Long roleId) {
        String encodedPassword = passwordEncoder.encode(password);
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        System.out.println(optionalRole);
        Role userRole = new Role();
        if(optionalRole.isPresent()){
            userRole = roleRepository.findByCode(optionalRole.get().getCode()).get();
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
}
