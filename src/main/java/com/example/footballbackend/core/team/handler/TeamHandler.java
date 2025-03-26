package com.example.footballbackend.core.team.handler;

import com.example.footballbackend.core.coach.CoachService;
import com.example.footballbackend.core.coach.dto.Coach;
import com.example.footballbackend.core.team.TeamService;
import com.example.footballbackend.core.team.converter.TeamConverter;
import com.example.footballbackend.core.team.dto.Team;
import com.example.footballbackend.core.team.web.contract.TeamReq;
import com.example.footballbackend.core.team.web.contract.TeamView;
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
public class TeamHandler {
    private final TeamConverter converter;
    private final TeamService service;
    private final CoachService coachService;
    private final MessageUtil messageUtil;

    public TeamHandler(TeamConverter converter,
                       TeamService service,
                       CoachService coachService,
                       MessageUtil messageUtil) {
        this.converter = converter;
        this.service = service;
        this.coachService = coachService;
        this.messageUtil = messageUtil;
    }

    public TeamView handlerGetTeamById(@NonNull Integer id) {
        return converter.toView(
                service.getTeamById(id)
                        .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("team.id.not-found", id)))
        );
    }

    public Page<TeamView> handlerGetAllTeam(@NonNull Pageable pageable) {
        Page<Team> teams = service.getAllTeam(pageable);
        List<TeamView> teamViewList = teams.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(teamViewList);
    }

    public TeamView handlerCreateTeam(@NonNull TeamReq req) {
        final Coach coach = coachService.getCoachById(req.coachId())
                .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("coach.id.not-found", req.coachId())));
        final Team team = new Team();

        team.setTeamName(req.teamName());
        team.setCoach(coach);
        team.setLeague(req.league());

        return converter.toView(
                service.saveTeam(team)
        );
    }

    public TeamView handlerUpdateTeamById(@NonNull Integer id, @NonNull TeamReq req) {
        final Team prototype = service.getReferenceOrNew(id);

        Optional.ofNullable(req.teamName()).ifPresent(prototype::setTeamName);
        Optional.ofNullable(coachService.getReferenceOrNew(req.coachId())).ifPresent(prototype::setCoach);
        Optional.ofNullable(req.league()).ifPresent(prototype::setLeague);

        return converter.toView(
                service.saveTeam(prototype)
        );
    }

    public void handlerDeleteTeamById(@NonNull Integer id) {
        service.deleteTeamById(id);
    }

}
