package org.workintech.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.workintech.entity.user.User;
import org.workintech.repository.user.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }
    @Test
    void findAll() {
        userService.findAll();
        verify(userRepository).findAll();
    }

    @Test
    void delete() {
        User user = new User();
        user.setId(1L);
        user.setEmail("customer@commerce.com");
        user.setName("Cust Omer");
        user.setPassword("123456");

        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        User deletedUser = userService.delete(user.getId());

        verify(userRepository).delete(user);
        assertEquals("Cust Omer",deletedUser.getName());
    }
}
