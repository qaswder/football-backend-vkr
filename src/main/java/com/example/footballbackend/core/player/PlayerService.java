package com.example.footballbackend.core.player;

import com.example.footballbackend.core.player.dto.Player;
import com.example.footballbackend.core.player.dto.PlayerRepo;
import com.example.footballbackend.error.ConflictResourceException;
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
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final MessageUtil messageUtil;

    public PlayerService(PlayerRepo playerRepo,
                         MessageUtil messageUtil) {
        this.playerRepo = playerRepo;
        this.messageUtil = messageUtil;
    }

    @Transactional(readOnly = true)
    public Page<Player> getAllPlayer(Pageable pageable) {
        return playerRepo.findAllPlayer(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Player> getPlayerById(@NonNull Integer id) {
        return playerRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Player> getPlayerByName(@NonNull String name, Pageable pageable) {
        return playerRepo.findPlayerByName(name, pageable);
    }

    @Transactional
    public Player savePlayer(@NonNull Player player) {
        try {
            return playerRepo.save(player);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Player getReferenceOrNew(@Nullable Integer id) {
        return id == null ? new Player() : playerRepo.getReferenceById(id);
    }

    @Transactional
    public void deletePlayerById(@NonNull Integer id) {
        playerRepo.deleteById(id);
    }
}
