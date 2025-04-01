package com.example.footballbackend.core.schedule;

import com.example.footballbackend.core.coach.dto.Coach;
import com.example.footballbackend.core.schedule.dto.Schedule;
import com.example.footballbackend.core.schedule.dto.ScheduleRepo;
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
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;
    private final MessageUtil messageUtil;

    public ScheduleService(ScheduleRepo scheduleRepo,
                           MessageUtil messageUtil){
        this.scheduleRepo = scheduleRepo;
        this.messageUtil = messageUtil;
    }

    @Transactional(readOnly = true)
    public Page<Schedule> getAllSchedule(Pageable pageable) {
        return scheduleRepo.findAllSchedule(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Schedule> getScheduleById(@NonNull Integer id) {
        return scheduleRepo.findById(id);
    }

    @Transactional
    public Schedule saveSchedule(@NonNull Schedule coach) {
        try {
            return scheduleRepo.save(coach);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Schedule getReferenceOrNew(@Nullable Integer id) {
        return id == null ? new Schedule() : scheduleRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteScheduleById(@NonNull Integer id) {
        Schedule coach = getScheduleById(id)
                .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("schedule.id.not-found", id)));

        scheduleRepo.delete(coach);
    }
}
