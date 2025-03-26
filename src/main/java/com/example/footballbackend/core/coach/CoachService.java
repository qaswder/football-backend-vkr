package com.example.footballbackend.core.coach;

import com.example.footballbackend.core.coach.dto.Coach;
import com.example.footballbackend.core.coach.dto.CoachRepo;
import com.example.footballbackend.core.player.dto.Player;
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
public class CoachService {
    private final CoachRepo coachRepo;
    private final MessageUtil messageUtil;

    public CoachService(CoachRepo coachRepo,
                        MessageUtil messageUtil){
        this.coachRepo = coachRepo;
        this.messageUtil = messageUtil;
    }

    @Transactional(readOnly = true)
    public Page<Coach> getAllCoach(Pageable pageable) {
        return coachRepo.findAllCoach(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Coach> getCoachById(@NonNull Integer id) {
        return coachRepo.findById(id);
    }

    @Transactional
    public Coach saveCoach(@NonNull Coach coach) {
        try {
            return coachRepo.save(coach);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Coach getReferenceOrNew(@Nullable Integer id) {
        return id == null ? new Coach() : coachRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteCoachById(@NonNull Integer id) {
        Coach coach = getCoachById(id)
                .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("coach.id.not-found", id)));

        coachRepo.delete(coach);
    }
}
