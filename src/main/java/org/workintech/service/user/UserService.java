package org.workintech.service.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.workintech.converter.DtoConverter;
import org.workintech.dto.user.LoginUserResponse;
import org.workintech.entity.user.Role;
import org.workintech.entity.user.User;
import org.workintech.exceptions.EcommerceException;
import org.workintech.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.workintech.utils.Helper.generateDummyToken;

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

    public User findByToken(String token){
        Optional<User> userOptional = userRepository.findUserByToken(token);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        throw new EcommerceException("The given token is wrong. So the user could not verify",HttpStatus.UNAUTHORIZED);
    }
    public String setUserToken(String email){
        User foundedUser = findByEmail(email);
        String generatedToken = generateDummyToken(foundedUser.getEmail());
        foundedUser.setToken(generatedToken);
        userRepository.save(foundedUser);
        return generatedToken;
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new EcommerceException("User is not found with id: " + id, HttpStatus.NOT_FOUND);
    }


    public User delete(long id) {
        User user = findById(id);
        if(user != null){
            userRepository.delete(user);
            return user;
        }
        throw new EcommerceException("User is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
