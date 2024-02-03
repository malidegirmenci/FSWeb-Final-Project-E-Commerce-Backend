package org.workintech.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.workintech.entity.user.User;
import org.workintech.exceptions.EcommerceException;
import org.workintech.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> {
                    System.out.println("User credentials are not valid");
                    return new UsernameNotFoundException("User credentials are not valid");
                });
    }

    public User findByToken(String token) {
        Optional<User> userOptional = userRepository.findUserByToken(token);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new EcommerceException("The given token is wrong. So the user could not verify", HttpStatus.UNAUTHORIZED);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new EcommerceException("User is not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    public User delete(long id) {
        User user = findById(id);
        if (user != null) {
            userRepository.delete(user);
            return user;
        }
        throw new EcommerceException("User is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
