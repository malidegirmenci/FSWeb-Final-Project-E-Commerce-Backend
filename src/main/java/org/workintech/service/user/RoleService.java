package org.workintech.service.user;

import org.workintech.dto.user.RoleResponse;
import org.workintech.entity.user.Role;

import java.util.List;

public interface RoleService {
    List<Role> saveAll(List<Role> roles);
    List<RoleResponse> getAll();
}
