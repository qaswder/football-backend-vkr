package com.example.footballbackend.core.user.converter;

import com.example.footballbackend.core.role.dto.Role;
import com.example.footballbackend.core.user.dto.User;
import com.example.footballbackend.core.user.web.contract.UserWithRoleView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserWithRoleView implements Converter<User, UserWithRoleView> {
    @Override
    public UserWithRoleView convert(User source) {
        Role role = source.getUserRole();

        return new UserWithRoleView(
                source.getId(),
                source.getUsername(),
                source.getEmail(),
                source.getLogin(),
                role.getDescription()
        );
    }
}
