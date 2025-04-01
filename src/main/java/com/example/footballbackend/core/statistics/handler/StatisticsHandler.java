package com.example.footballbackend.core.statistics.handler;

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
import java.util.Optional;

@Component
public class StatisticsHandler {
    private final StatisticsConverter converter;
    private final StatisticsService service;
    private final PlayerService playerService;
    private final MessageUtil messageUtil;

    public StatisticsHandler(StatisticsConverter converter,
                             StatisticsService service,
                             PlayerService playerService,
                             MessageUtil messageUtil){
        this.converter = converter;
        this.service = service;
        this.playerService = playerService;
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
                .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("player.id.not-found", req.playerId())));
        final Statistics match = new Statistics();

        match.setPlayer(player);
        match.setGoals(req.goals());
        match.setAssists(req.assists());
        match.setYellowCards(req.yellowCards());
        match.setRedCards(req.redCards());
        match.setSeason(req.season());

        return converter.toView(
                service.saveStatistics(match)
        );
    }

    public StatisticsView handlerUpdateStatisticsById(@NonNull Integer id, @NonNull StatisticsReq req) {
        final Statistics prototype = service.getReferenceOrNew(id);

        Optional.ofNullable(req.playerId())
                .map(playerId -> playerService.getPlayerById(playerId)
                        .orElseThrow(() -> new NotFoundException(
                                messageUtil.getMessage("player.id.not-found", playerId))))
                .ifPresent(prototype::setPlayer);

        Optional.ofNullable(req.goals()).ifPresent(prototype::setGoals);
        Optional.ofNullable(req.assists()).ifPresent(prototype::setAssists);
        Optional.ofNullable(req.yellowCards()).ifPresent(prototype::setYellowCards);
        Optional.ofNullable(req.redCards()).ifPresent(prototype::setRedCards);
        Optional.ofNullable(req.season()).ifPresent(prototype::setSeason);

        return converter.toView(
                service.saveStatistics(prototype)
        );
    }

    public void handlerDeleteStatisticsById(@NonNull Integer id) {
        service.deleteStatisticsById(id);
    }
}
