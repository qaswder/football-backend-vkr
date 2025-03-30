package com.example.footballbackend.core.match;

import com.example.footballbackend.core.match.dto.Match;
import com.example.footballbackend.core.match.dto.MatchRepo;
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
public class MatchService {
    private final MatchRepo matchRepo;
    private final MessageUtil messageUtil;

    public MatchService(MatchRepo matchRepo,
                        MessageUtil messageUtil){
        this.matchRepo = matchRepo;
        this.messageUtil = messageUtil;
    }

    @Transactional(readOnly = true)
    public Page<Match> getAllMatch(Pageable pageable) {
        return matchRepo.findAllMatch(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Match> getMatchById(@NonNull Integer id) {
        return matchRepo.findById(id);
    }

    @Transactional
    public Match saveMatch(@NonNull Match match) {
        try {
            return matchRepo.save(match);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Match getReferenceOrNew(@Nullable Integer id) {
        return id == null ? new Match() : matchRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteMatchById(@NonNull Integer id) {
        Match match = getMatchById(id)
                .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("match.id.not-found", id)));

        matchRepo.delete(match);
    }
}
