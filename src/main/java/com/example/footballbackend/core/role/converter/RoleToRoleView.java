package com.example.footballbackend.core.role.converter;

import com.example.footballbackend.core.role.dto.Role;
import com.example.footballbackend.core.role.web.contract.RoleView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToRoleView implements Converter<Role, RoleView> {
    @Override
    public RoleView convert(Role source) {
        return new RoleView(
                source.getId(),
                source.getDescription()
        );
    }
}
