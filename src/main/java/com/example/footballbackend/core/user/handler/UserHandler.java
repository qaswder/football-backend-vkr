package com.example.footballbackend.core.user.handler;

import com.example.footballbackend.core.user.UserService;
import com.example.footballbackend.core.user.converter.UserConverter;
import com.example.footballbackend.core.user.dto.RoleEnum;
import com.example.footballbackend.core.user.dto.User;
import com.example.footballbackend.core.user.web.contract.UserCreateReq;
import com.example.footballbackend.core.user.web.contract.UserUpdateReq;
import com.example.footballbackend.core.user.web.contract.UserView;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserHandler {
    private final UserConverter converter;
    private final UserService service;
    private final MessageUtil messageUtil;

    public UserHandler(UserConverter converter,
                       UserService service,
                       MessageUtil messageUtil){
        this.converter = converter;
        this.service = service;
        this.messageUtil = messageUtil;
    }

    public UserView handlerGetUserById(@NonNull Integer id) {
        return converter.toView(
                service.getUserById(id)
                        .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("user.id.not-found", id)))
        );
    }

    public Page<UserView> handlerGetAllUser(@NonNull Pageable pageable) {
        Page<User> users = service.getAllUser(pageable);
        List<UserView> userViewList = users.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(userViewList);
    }

    public Page<UserView> handlerGetUserByUsername(@NonNull String username,
                                               @NonNull Pageable pageable) {
        Page<User> users = service.getUserByUsername(username, pageable);
        if(users.isEmpty()){
            throw new NotFoundException(messageUtil.getMessage("user.username.not-found", username));
        }
        List<UserView> userViewList = users.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(userViewList);
    }

    public UserView handlerCreateUser(@NonNull UserCreateReq req) {
        final User user = new User();
        user.setUsername(req.username());
        user.setEmail(req.email());
        user.setLogin(req.login());
        user.setPassword(req.password());
        user.setUserRole(RoleEnum.getRoleByCode(req.userRole()));

        return converter.toView(
                service.saveUser(user)
        );
    }

    public UserView handlerUpdateUserById(@NonNull Integer id, @NonNull UserUpdateReq req) {
        final User prototype = service.getReferenceOrNew(id);
        prototype.setUsername(req.username());

        return converter.toView(
                service.saveUser(prototype)
        );
    }

    public void handlerDeleteUserById(@NonNull Integer id) {
        service.deleteUserById(id);
    }
}
