package org.workintech.controller.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.workintech.entity.user.User;
import org.workintech.service.user.UserService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Test
    void remove() throws Exception{
        User user = new User();
        user.setId(1L);
        user.setName("Cust Omer");
        user.setEmail("customer@commerce.com");
        user.setPassword("1234");

        when(userService.findById(1)).thenReturn(user);
        when(userService.delete(user.getId())).thenReturn(user);

        mockMvc.perform(delete("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("hzl@test.com"));
        verify(userService).delete(1L);
    }
}