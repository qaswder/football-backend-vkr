package com.example.footballbackend.core.result.handler;

import com.example.footballbackend.core.result.ResultService;
import com.example.footballbackend.core.result.converter.ResultConverter;
import com.example.footballbackend.core.result.dto.Result;
import com.example.footballbackend.core.result.web.contract.ResultReq;
import com.example.footballbackend.core.result.web.contract.ResultView;
import com.example.footballbackend.core.team.TeamService;
import com.example.footballbackend.core.team.dto.Team;
import com.example.footballbackend.core.tournament.TournamentService;
import com.example.footballbackend.core.tournament.dto.Tournament;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultHandler {
    private final ResultConverter converter;
    private final ResultService service;
    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final MessageUtil messageUtil;

    public ResultHandler(ResultConverter converter,
                         ResultService service,
                         TeamService teamService,
                         TournamentService tournamentService,
                         MessageUtil messageUtil) {
        this.converter = converter;
        this.service = service;
        this.teamService = teamService;
        this.tournamentService = tournamentService;
        this.messageUtil = messageUtil;
    }

    public ResultView handlerGetResultById(@NonNull Integer id) {
        return converter.toView(
                service.getResultById(id)
                        .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("result.id.not-found", id)))
        );
    }

    public Page<ResultView> handlerGetAllResult(@NonNull Pageable pageable) {
        Page<Result> results = service.getAllResult(pageable);
        List<ResultView> resultViewList = results.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(resultViewList);
    }

    public ResultView handlerCreateResult(@NonNull ResultReq req) {
        final Result result = new Result();
        final Team team = teamService.getTeamById(req.teamId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("team.id.not-found", req.teamId())));
        final Tournament tournament = tournamentService.getTournamentById(req.tournamentId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("tournament.id.not-found", req.teamId())));

        result.setPosition(req.position());
        result.setPlayed(req.played());
        result.setWins(req.wins());
        result.setDraws(req.draws());
        result.setLosses(req.losses());
        result.setPoints(req.points());
        result.setTeam(team);
        result.setTournament(tournament);

        return converter.toView(
                service.saveResult(result)
        );
    }

    public ResultView handlerUpdateResultById(@NonNull Integer id, @NonNull ResultReq req) {
        final Result prototype = service.getReferenceOrNew(id);

        final Team team = teamService.getTeamById(req.teamId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("team.id.not-found", req.teamId())));
        final Tournament tournament = tournamentService.getTournamentById(req.tournamentId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("tournament.id.not-found", req.teamId())));

        prototype.setPosition(req.position());
        prototype.setPlayed(req.played());
        prototype.setWins(req.wins());
        prototype.setDraws(req.draws());
        prototype.setLosses(req.losses());
        prototype.setPoints(req.points());
        prototype.setTeam(team);
        prototype.setTournament(tournament);

        return converter.toView(
                service.saveResult(prototype)
        );
    }

    public void handlerDeleteResultById(@NonNull Integer id) {
        service.deleteResultById(id);
    }

}
