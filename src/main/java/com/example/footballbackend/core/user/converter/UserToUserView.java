package com.example.footballbackend.core.user.converter;

import com.example.footballbackend.core.user.dto.User;
import com.example.footballbackend.core.user.web.contract.UserView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserView implements Converter<User, UserView> {
    @Override
    public UserView convert(User source) {
        return new UserView(
                source.getId(),
                source.getUsername(),
                source.getEmail(),
                source.getLogin()
        );
    }
}
