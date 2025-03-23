package com.example.footballbackend.core.role;

import com.example.footballbackend.core.role.dto.Role;
import com.example.footballbackend.core.role.dto.RoleRepo;
import com.example.footballbackend.core.user.dto.User;
import com.example.footballbackend.error.ConflictResourceException;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepo roleRepo;
    private final MessageUtil messageUtil;

    public RoleService (RoleRepo roleRepo,
                        MessageUtil messageUtil){
        this.roleRepo = roleRepo;
        this.messageUtil = messageUtil;
    }

    @Transactional(readOnly = true)
    public Page<Role> getAllRole(Pageable pageable){
        return roleRepo.findAllRole(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Role> getRoleById(@NonNull Integer id){
        return roleRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Role> getRoleByDescription(@NonNull String description){
        return roleRepo.findRoleByDescription(description);
    }

    @Transactional
    public Role saveRole(@NonNull Role role){
        if(roleRepo.existsByRoleNameOrDescription(role.getRoleName(), role.getDescription())){
            throw new ConflictResourceException(messageUtil.getMessage("role.exist", role.getRoleName(), role.getDescription()));
        }
        try{
            return roleRepo.save(role);
        }catch (DataIntegrityViolationException e){
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Role getReferenceOrNew(@Nullable Integer id){
        return id == null ? new Role() : roleRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteRoleById(@NonNull Integer id){
        Role role = getRoleById(id)
                .orElseThrow(()->new NotFoundException(messageUtil.getMessage("role.id.not-found", id)));

        roleRepo.delete(role);
    }
}
