package com.example.footballbackend.core.user.converter;

import com.example.footballbackend.core.user.dto.User;
import com.example.footballbackend.core.user.web.contract.UserView;
import com.example.footballbackend.core.user.web.contract.UserWithRoleView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private UserToUserView toUserView;
    private UserToUserWithRoleView toUserWithRoleView;

    public UserConverter(UserToUserView toUserView,
                         UserToUserWithRoleView toUserWithRoleView){
        this.toUserView = toUserView;
        this.toUserWithRoleView = toUserWithRoleView;
    }

    public UserView toView(@NonNull User user){
        return toUserView.convert(user);
    }

    public UserWithRoleView toWithRoleView(@NonNull User user){
        return toUserWithRoleView.convert(user);
    }
}
