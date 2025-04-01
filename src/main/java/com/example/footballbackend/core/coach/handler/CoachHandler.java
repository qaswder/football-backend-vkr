package com.example.footballbackend.core.coach.handler;

import com.example.footballbackend.core.coach.CoachService;
import com.example.footballbackend.core.coach.converter.CoachConverter;
import com.example.footballbackend.core.coach.dto.Coach;
import com.example.footballbackend.core.coach.web.contract.CoachReq;
import com.example.footballbackend.core.coach.web.contract.CoachView;
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
public class CoachHandler {
    private final CoachConverter converter;
    private final CoachService service;
    private final MessageUtil messageUtil;

    public CoachHandler(CoachConverter converter,
                        CoachService service,
                        MessageUtil messageUtil){
        this.converter = converter;
        this.service = service;
        this.messageUtil = messageUtil;
    }

    public CoachView handlerGetCoachById(@NonNull Integer id) {
        return converter.toView(
                service.getCoachById(id)
                        .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("coach.id.not-found", id)))
        );
    }

    public Page<CoachView> handlerGetAllCoach(@NonNull Pageable pageable) {
        Page<Coach> coach = service.getAllCoach(pageable);
        List<CoachView> coachViewList = coach.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(coachViewList);
    }

    public CoachView handlerCreateCoach(@NonNull CoachReq req) {
        final Coach coach = new Coach();

        coach.setSurname(req.surname());
        coach.setName(req.name());
        coach.setPatronymic(req.patronymic());

        return converter.toView(
                service.saveCoach(coach)
        );
    }

    public CoachView handlerUpdateCoachById(@NonNull Integer id, @NonNull CoachReq req) {
        final Coach prototype = service.getReferenceOrNew(id);

        Optional.ofNullable(req.surname()).ifPresent(prototype::setSurname);
        Optional.ofNullable(req.name()).ifPresent(prototype::setName);
        Optional.ofNullable(req.patronymic()).ifPresent(prototype::setPatronymic);

        return converter.toView(
                service.saveCoach(prototype)
        );
    }

    public void handlerDeleteCoachById(@NonNull Integer id) {
        service.deleteCoachById(id);
    }

}
