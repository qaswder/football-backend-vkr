package com.example.footballbackend.core.role.handler;

import com.example.footballbackend.core.role.RoleService;
import com.example.footballbackend.core.role.converter.RoleConverter;
import com.example.footballbackend.core.role.dto.Role;
import com.example.footballbackend.core.role.web.contract.RoleCreateReq;
import com.example.footballbackend.core.role.web.contract.RoleUpdateReq;
import com.example.footballbackend.core.role.web.contract.RoleView;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleHandler {
    private final RoleConverter converter;
    private final RoleService service;
    private final MessageUtil messageUtil;

    public RoleHandler (RoleConverter converter,
                        RoleService service,
                        MessageUtil messageUtil){
        this.converter = converter;
        this.service = service;
        this.messageUtil = messageUtil;
    }

    public RoleView handlerGetRoleById(@NonNull Integer id) {
        return converter.toView(
                service.getRoleById(id)
                        .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("role.id.not-found", id)))
        );
    }

    public Page<RoleView> handlerGetAllRole(@NonNull Pageable pageable) {
        Page<Role> roles = service.getAllRole(pageable);
        List<RoleView> roleViewsList = roles.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(roleViewsList);
    }

    public RoleView handlerCreateRole(@NonNull RoleCreateReq req) {
        final Role role = new Role();

        role.setRoleName(req.roleName());
        role.setDescription(req.description());

        return converter.toView(
                service.saveRole(role)
        );
    }

    public RoleView handlerUpdateRoleById(@NonNull Integer id, @NonNull RoleUpdateReq req) {
        final Role prototype = service.getReferenceOrNew(id);
        prototype.setRoleName(req.roleName());
        prototype.setDescription(req.description());

        return converter.toView(
                service.saveRole(prototype)
        );
    }

    public void handlerDeleteRoleById(@NonNull Integer id) {
        service.deleteRoleById(id);
    }
}
