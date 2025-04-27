package com.example.footballbackend.core.statistics.handler;

import com.example.footballbackend.core.match.MatchService;
import com.example.footballbackend.core.match.dto.Match;
import com.example.footballbackend.core.player.PlayerService;
import com.example.footballbackend.core.player.dto.Player;
import com.example.footballbackend.core.statistics.StatisticsService;
import com.example.footballbackend.core.statistics.converter.StatisticsConverter;
import com.example.footballbackend.core.statistics.dto.Statistics;
import com.example.footballbackend.core.statistics.web.contract.StatisticsReq;
import com.example.footballbackend.core.statistics.web.contract.StatisticsView;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticsHandler {
    private final StatisticsConverter converter;
    private final StatisticsService service;
    private final PlayerService playerService;
    private final MatchService matchService;
    private final MessageUtil messageUtil;

    public StatisticsHandler(StatisticsConverter converter,
                             StatisticsService service,
                             PlayerService playerService,
                             MatchService matchService,
                             MessageUtil messageUtil){
        this.converter = converter;
        this.service = service;
        this.playerService = playerService;
        this.matchService = matchService;
        this.messageUtil = messageUtil;
    }

    public StatisticsView handlerGetStatisticsById(@NonNull Integer id) {
        return converter.toView(
                service.getStatisticsById(id)
                        .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("statistics.id.not-found", id)))
        );
    }

    public Page<StatisticsView> handlerGetAllStatistics(@NonNull Pageable pageable) {
        Page<Statistics> match = service.getAllStatistics(pageable);
        List<StatisticsView> matchViewList = match.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(matchViewList);
    }

    public StatisticsView handlerCreateStatistics(@NonNull StatisticsReq req) {
        final Player player = playerService.getPlayerById(req.playerId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("player.id.not-found", req.playerId())));
        final Match match = matchService.getMatchById(req.matchId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("match.id.not-found", req.matchId())));
        final Statistics statistics = new Statistics();

        statistics.setPlayer(player);
        statistics.setMatch(match);
        statistics.setGoals(req.goals());
        statistics.setAssists(req.assists());
        statistics.setYellowCards(req.yellowCards());
        statistics.setRedCards(req.redCards());
        statistics.setPlayedMinutes(req.playedMinutes());
        statistics.setShots(req.shots());
        statistics.setPasses(req.passes());
        statistics.setSeason(req.season());

        return converter.toView(
                service.saveStatistics(statistics)
        );
    }

    public StatisticsView handlerUpdateStatisticsById(@NonNull Integer id, @NonNull StatisticsReq req) {
        final Player player = playerService.getPlayerById(req.playerId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("player.id.not-found", req.playerId())));
        final Match match = matchService.getMatchById(req.matchId())
                .orElseThrow(() ->
                        new NotFoundException(messageUtil.getMessage("match.id.not-found", req.matchId())));
        final Statistics prototype = service.getReferenceOrNew(id);

        prototype.setPlayer(player);
        prototype.setMatch(match);
        prototype.setGoals(req.goals());
        prototype.setAssists(req.assists());
        prototype.setYellowCards(req.yellowCards());
        prototype.setRedCards(req.redCards());
        prototype.setPlayedMinutes(req.playedMinutes());
        prototype.setShots(req.shots());
        prototype.setPasses(req.passes());
        prototype.setSeason(req.season());

        return converter.toView(
                service.saveStatistics(prototype)
        );
    }

    public void handlerDeleteStatisticsById(@NonNull Integer id) {
        service.deleteStatisticsById(id);
    }
}
