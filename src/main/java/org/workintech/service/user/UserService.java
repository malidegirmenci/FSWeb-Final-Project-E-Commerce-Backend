package org.workintech.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.workintech.entity.user.Role;
import org.workintech.entity.user.User;
import org.workintech.exceptions.EcommerceException;
import org.workintech.repository.user.UserRepository;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> {
                    System.out.println("User credentials are not valid");
                    throw new UsernameNotFoundException("User credentials are not valid");
                });
    }
    public User findByEmail(String email){
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new EcommerceException("The given email does not exist", HttpStatus.BAD_REQUEST);
    }

}
