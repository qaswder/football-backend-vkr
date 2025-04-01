package com.example.footballbackend.core.schedule.handler;

import com.example.footballbackend.core.coach.CoachService;
import com.example.footballbackend.core.coach.converter.CoachConverter;
import com.example.footballbackend.core.coach.dto.Coach;
import com.example.footballbackend.core.coach.web.contract.CoachReq;
import com.example.footballbackend.core.coach.web.contract.CoachView;
import com.example.footballbackend.core.match.MatchService;
import com.example.footballbackend.core.match.dto.Match;
import com.example.footballbackend.core.schedule.ScheduleService;
import com.example.footballbackend.core.schedule.converter.ScheduleConverter;
import com.example.footballbackend.core.schedule.dto.Schedule;
import com.example.footballbackend.core.schedule.web.contract.ScheduleReq;
import com.example.footballbackend.core.schedule.web.contract.ScheduleView;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ScheduleHandler {

    private final ScheduleConverter converter;
    private final ScheduleService service;
    private final MatchService matchService;
    private final MessageUtil messageUtil;

    public ScheduleHandler(ScheduleConverter converter,
                        ScheduleService service,
                        MatchService matchService,
                        MessageUtil messageUtil){
        this.converter = converter;
        this.service = service;
        this.matchService = matchService;
        this.messageUtil = messageUtil;
    }


    public ScheduleView handlerGetScheduleById(@NonNull Integer id) {
        return converter.toView(
                service.getScheduleById(id)
                        .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("schedule.id.not-found", id)))
        );
    }

    public Page<ScheduleView> handlerGetAllSchedule(@NonNull Pageable pageable) {
        Page<Schedule> schedule = service.getAllSchedule(pageable);
        List<ScheduleView> scheduleViewsList = schedule.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(scheduleViewsList);
    }

    public ScheduleView handlerCreateSchedule(@NonNull ScheduleReq req) {
        final Match match = matchService.getMatchById(req.matchId())
                .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("match.id.not-found", req.matchId())));
        final Schedule schedule = new Schedule();

        schedule.setMatch(match);
        schedule.setStatusMatch(req.statusMatch());

        return converter.toView(
                service.saveSchedule(schedule)
        );
    }

    public ScheduleView handlerUpdateScheduleById(@NonNull Integer id, @NonNull ScheduleReq req) {
        final Schedule prototype = service.getReferenceOrNew(id);

        Optional.ofNullable(req.statusMatch()).ifPresent(prototype::setStatusMatch);

        return converter.toView(
                service.saveSchedule(prototype)
        );
    }

    public void handlerDeleteScheduleById(@NonNull Integer id) {
        service.deleteScheduleById(id);
    }
}
