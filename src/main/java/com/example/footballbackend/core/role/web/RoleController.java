package com.example.footballbackend.core.role.web;

import com.example.footballbackend.core.role.handler.RoleHandler;
import com.example.footballbackend.core.role.web.contract.RoleCreateReq;
import com.example.footballbackend.core.role.web.contract.RoleUpdateReq;
import com.example.footballbackend.core.role.web.contract.RoleView;
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
@RequestMapping("/api/roles")
@Tag(name = "Роли пользователей")
public class RoleController {
    private final RoleHandler handler;

    public RoleController(RoleHandler handler){
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение роли по id")
    public RoleView getRoleById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetRoleById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всех ролей")
    public Page<RoleView> getAllRoles(@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllRole(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Создание роли")
    public RoleView createRole(@Valid @RequestBody @NotNull RoleCreateReq req) {
        return handler.handlerCreateRole(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение роли")
    public RoleView updateRoleById(@PathVariable @NotNull Integer id,
                                   @Valid @RequestBody @NotNull RoleUpdateReq req) {
        return handler.handlerUpdateRoleById(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление роли")
    public void deleteRoleById(@PathVariable @NotNull Integer id) {
        handler.handlerDeleteRoleById(id);
    }
}
