package com.example.footballbackend.core.user.handler;

import com.example.footballbackend.core.role.RoleService;
import com.example.footballbackend.core.role.dto.Role;
import com.example.footballbackend.core.user.UserService;
import com.example.footballbackend.core.user.converter.UserConverter;
import com.example.footballbackend.core.user.dto.User;
import com.example.footballbackend.core.user.web.contract.UserCreateReq;
import com.example.footballbackend.core.user.web.contract.UserUpdateReq;
import com.example.footballbackend.core.user.web.contract.UserView;
import com.example.footballbackend.core.user.web.contract.UserWithRoleView;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserHandler {
    private final UserConverter converter;
    private final UserService service;
    private final MessageUtil messageUtil;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public UserHandler(UserConverter converter,
                       UserService service,
                       MessageUtil messageUtil,
                       RoleService roleService,
                       PasswordEncoder encoder) {
        this.converter = converter;
        this.service = service;
        this.messageUtil = messageUtil;
        this.roleService = roleService;
        this.encoder = encoder;

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
        if (users.isEmpty()) {
            throw new NotFoundException(messageUtil.getMessage("user.username.not-found", username));
        }
        List<UserView> userViewList = users.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(userViewList);
    }

    public Page<UserWithRoleView> handlerGetUserByRole(@NonNull String description,
                                                       @NonNull Pageable pageable) {
        Page<User> users = service.getUserByRole(description, pageable);
        if (users.isEmpty()) {
            throw new NotFoundException(messageUtil.getMessage("user.role.description.not-found", description));
        }
        List<UserWithRoleView> userViewList = users.stream()
                .map(converter::toWithRoleView)
                .toList();
        return new PageImpl<>(userViewList);
    }

    public UserView handlerCreateUser(@NonNull UserCreateReq req) {
        final Role defaultRole = roleService
                .getRoleByDescription("Пользователь")
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("role.description.not-found", "Пользователь"))
                );
        final User user = new User();

        if(service.getUserByEmail(req.email()).isPresent()){
            return null;
        }

        user.setUsername(req.username());
        user.setEmail(req.email());
        user.setPassword(encoder.encode(req.password()));
        user.setUserRole(defaultRole);

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
