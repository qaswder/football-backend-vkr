package com.example.footballbackend.core.user.web;

import com.example.footballbackend.core.user.handler.UserHandler;
import com.example.footballbackend.core.user.web.contract.UserCreateReq;
import com.example.footballbackend.core.user.web.contract.UserUpdateReq;
import com.example.footballbackend.core.user.web.contract.UserView;
import com.example.footballbackend.core.user.web.contract.UserWithRoleView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Пользователи")
public class UserController {
    private final UserHandler handler;

    public UserController(UserHandler handler){
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по id")
    public UserView getUserById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всех пользователей")
    public Page<UserView> getAllUsers(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllUser(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    @Operation(summary = "Получение пользователя по имени")
    public Page<UserView> getUserByUsername(@RequestParam @NotNull String username,
                                         @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                         @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetUserByUsername(username, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/roles")
    @Operation(summary = "Получение пользователей по роли")
    public Page<UserWithRoleView> getUserByRole(@RequestParam @NotNull String description,
                                                    @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                                    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetUserByRole(description, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    @Operation(summary = "Создание пользователя")
    public UserView createUser(@Valid @RequestBody @NotNull UserCreateReq req) {
        return handler.handlerCreateUser(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение пользователя")
    public UserView updateUserById(@PathVariable @NotNull Integer id,
                                   @Valid @RequestBody @NotNull UserUpdateReq req) {
        return handler.handlerUpdateUserById(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя")
    public void deleteUserById(@PathVariable @NotNull Integer id) {
        handler.handlerDeleteUserById(id);
    }
}
