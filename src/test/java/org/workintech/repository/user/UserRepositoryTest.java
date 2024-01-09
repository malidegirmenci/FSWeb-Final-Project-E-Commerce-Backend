package org.workintech.repository.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.workintech.entity.user.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void createUser(String email){
        User user = new User();
        user.setName("Cust Omer");
        user.setEmail(email);
        user.setPassword("1234");
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isPresent()){
            User foundUser = userRepository.findUserByEmail(email).get();
            if(foundUser == null){
                userRepository.save(user);
            }
        }
    }

    @BeforeEach
    void setUp() {
        createUser("customer@commerce.com");
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @DisplayName("Can find user by email")
    @Test
    void findUserByEmail() {
        Optional<User> optionalUser = userRepository.findUserByEmail("customer@commerce.com");
        if(optionalUser.isPresent()){
            User foundUser = userRepository.findUserByEmail("customer@commerce.com").get();
            assertNotNull(foundUser);
            assertEquals("Cust Omer",foundUser.getName());
        }
    }

    @DisplayName("Cant find user by email")
    @Test
    void findUserByEmailFail(){
        Optional<User> optionalUser = userRepository.findUserByEmail("store@store.com");
        assertFalse(optionalUser.isPresent());
    }
}