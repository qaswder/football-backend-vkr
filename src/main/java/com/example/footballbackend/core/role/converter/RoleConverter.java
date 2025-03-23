package com.example.footballbackend.core.role.converter;

import com.example.footballbackend.core.role.dto.Role;
import com.example.footballbackend.core.role.web.contract.RoleView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    private final RoleToRoleView toRoleView;

    public RoleConverter (RoleToRoleView toRoleView){
        this.toRoleView = toRoleView;
    }

    public RoleView toView(@NonNull Role role){
        return toRoleView.convert(role);
    }
}
