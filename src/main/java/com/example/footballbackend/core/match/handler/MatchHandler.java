package com.example.footballbackend.core.match.handler;

import com.example.footballbackend.core.coach.dto.Coach;
import com.example.footballbackend.core.coach.web.contract.CoachReq;
import com.example.footballbackend.core.coach.web.contract.CoachView;
import com.example.footballbackend.core.match.MatchService;
import com.example.footballbackend.core.match.converter.MatchConverter;
import com.example.footballbackend.core.match.dto.Match;
import com.example.footballbackend.core.match.web.contract.MatchReq;
import com.example.footballbackend.core.match.web.contract.MatchView;
import com.example.footballbackend.core.team.TeamService;
import com.example.footballbackend.core.team.dto.Team;
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
public class MatchHandler {
    private final MatchConverter converter;
    private final MatchService service;
    private final TeamService teamService;
    private final MessageUtil messageUtil;

    public MatchHandler(MatchConverter converter,
                        MatchService service,
                        TeamService teamService,
                        MessageUtil messageUtil){
        this.converter = converter;
        this.service = service;
        this.teamService = teamService;
        this.messageUtil = messageUtil;
    }

    public MatchView handlerGetMatchById(@NonNull Integer id) {
        return converter.toView(
                service.getMatchById(id)
                        .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("match.id.not-found", id)))
        );
    }

    public Page<MatchView> handlerGetAllMatch(@NonNull Pageable pageable) {
        Page<Match> match = service.getAllMatch(pageable);
        List<MatchView> matchViewList = match.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(matchViewList);
    }

    public MatchView handlerCreateMatch(@NonNull MatchReq req) {
        final Team homeTeam = teamService.getTeamById(req.teamHomeId())
                .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("team.id.not-found", req.teamHomeId())));
        final Team awayTeam = teamService.getTeamById(req.teamAwayId())
                .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("team.id.not-found", req.teamAwayId())));
        final Match match = new Match();

        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setScore(req.score());
        match.setDateTime(req.date());

        return converter.toView(
                service.saveMatch(match)
        );
    }

    public MatchView handlerUpdateMatchById(@NonNull Integer id, @NonNull MatchReq req) {
        final Match prototype = service.getReferenceOrNew(id);

        Optional.ofNullable(req.teamHomeId())
                .map(teamId -> teamService.getTeamById(teamId)
                        .orElseThrow(() -> new NotFoundException(
                                messageUtil.getMessage("team.id.not-found", teamId))))
                .ifPresent(prototype::setHomeTeam);

        Optional.ofNullable(req.teamAwayId())
                .map(teamId -> teamService.getTeamById(teamId)
                        .orElseThrow(() -> new NotFoundException(
                                messageUtil.getMessage("team.id.not-found", teamId))))
                .ifPresent(prototype::setAwayTeam);

        Optional.ofNullable(req.score()).ifPresent(prototype::setScore);
        Optional.ofNullable(req.date()).ifPresent(prototype::setDateTime);

        return converter.toView(
                service.saveMatch(prototype)
        );
    }

    public void handlerDeleteMatchById(@NonNull Integer id) {
        service.deleteMatchById(id);
    }

}
