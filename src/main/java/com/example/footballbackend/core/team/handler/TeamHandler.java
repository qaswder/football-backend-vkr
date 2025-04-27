package com.example.footballbackend.core.team.handler;

import com.example.footballbackend.core.staff.dto.Staff;
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

@Component
public class TeamHandler {
    private final TeamConverter converter;
    private final TeamService service;
    private final MessageUtil messageUtil;

    public TeamHandler(TeamConverter converter,
                       TeamService service,
                       MessageUtil messageUtil) {
        this.converter = converter;
        this.service = service;
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
        return teams.map(converter::toView);
    }

    public TeamView handlerCreateTeam(@NonNull TeamReq req) {
        final Team team = new Team();

        team.setTeamName(req.teamName());
        team.setTeamShortName(req.teamShortName());
        team.setStadium(req.stadium());
        team.setLogoUrl(req.logoUrl());

        return converter.toView(
                service.saveTeam(team)
        );
    }

    public TeamView handlerUpdateTeamById(@NonNull Integer id, @NonNull TeamReq req) {
        final Team prototype = service.getReferenceOrNew(id);

        prototype.setTeamName(req.teamName());
        prototype.setTeamShortName(req.teamShortName());
        prototype.setStadium(req.stadium());
        prototype.setLogoUrl(req.logoUrl());

        return converter.toView(
                service.saveTeam(prototype)
        );
    }

    public void handlerDeleteTeamById(@NonNull Integer id) {
        service.deleteTeamById(id);
    }

}
