package com.example.footballbackend.core.user.converter;

import com.example.footballbackend.core.user.dto.User;
import com.example.footballbackend.core.user.web.contract.UserView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private UserToUserView toUserView;

    public UserConverter(UserToUserView toUserView){
        this.toUserView = toUserView;
    }

    public UserView toView(@NonNull User user){
        return toUserView.convert(user);
    }
}
