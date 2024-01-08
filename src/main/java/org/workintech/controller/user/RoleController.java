package org.workintech.controller.user;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.workintech.entity.user.Role;
import org.workintech.service.user.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;
    private static final String GET_ALL_ROLES = "https://workintech-fe-ecommerce.onrender.com/roles";
    private RestTemplateBuilder restTemplateBuilder;
    @PostMapping("/all")
    public String saveAll(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JsonNode> responses = restTemplate.getForEntity(GET_ALL_ROLES, JsonNode.class);
        List<Role> roles = new ArrayList<>();
        for (JsonNode node : Objects.requireNonNull(responses.getBody())) {
            Long id = node.get("id").asLong();
            String name = node.get("name").asText();
            String code = node.get("code").asText();
            Role role = new Role();
            role.setId(id);
            role.setName(name);
            role.setCode(code);
            roles.add(role);
        }
        roleService.saveAll(roles);
        return "All roles fetched successfully";
    }
}
