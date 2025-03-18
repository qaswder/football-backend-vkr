package com.example.footballbackend.core.user;

import com.example.footballbackend.core.user.dto.User;
import com.example.footballbackend.core.user.dto.UserRepo;
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
public class UserService {
    private final UserRepo userRepo;
    private final MessageUtil messageUtil;

    public UserService(UserRepo userRepo,
                       MessageUtil messageUtil) {
        this.userRepo = userRepo;
        this.messageUtil = messageUtil;
    }

    @Transactional(readOnly = true)
    public Page<User> getAllUser(Pageable pageable){
        return userRepo.findAllUser(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(@NonNull Integer id){
        return userRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<User> getUserByUsername(@NonNull String username,Pageable pageable){
        return userRepo.findBookByUsername(username, pageable);
    }

    @Transactional
    public User saveUser(@NonNull User user){
        try{
            return userRepo.save(user);
        }catch (DataIntegrityViolationException e){
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public User getReferenceOrNew(@Nullable Integer id){
        return id == null ? new User() : userRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteUserById(@NonNull Integer id){
        User user = getUserById(id)
                .orElseThrow(()->new NotFoundException(messageUtil.getMessage("user.id.not-found", id)));

        userRepo.delete(user);
    }
}
