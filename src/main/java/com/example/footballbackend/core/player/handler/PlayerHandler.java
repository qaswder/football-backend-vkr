package com.example.footballbackend.core.player.handler;

import com.example.footballbackend.core.player.PlayerService;
import com.example.footballbackend.core.player.converter.PlayerConverter;
import com.example.footballbackend.core.player.dto.Player;
import com.example.footballbackend.core.player.dto.PositionEnum;
import com.example.footballbackend.core.player.web.contract.PlayerReq;
import com.example.footballbackend.core.player.web.contract.PlayerView;
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

@Component
public class PlayerHandler {
    private final PlayerConverter converter;
    private final PlayerService service;
    private final TeamService teamService;
    private final MessageUtil messageUtil;

    public PlayerHandler(PlayerConverter converter,
                         PlayerService service,
                         TeamService teamService,
                         MessageUtil messageUtil) {
        this.converter = converter;
        this.service = service;
        this.teamService = teamService;
        this.messageUtil = messageUtil;
    }

    public PlayerView handlerGetPlayerById(@NonNull Integer id) {
        return converter.toView(
                service.getPlayerById(id)
                        .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("player.id.not-found", id)))
        );
    }

    public Page<PlayerView> handlerGetAllPlayer(@NonNull Pageable pageable) {
        Page<Player> players = service.getAllPlayer(pageable);
        return players.map(converter::toView);
    }

    public Page<PlayerView> handlerGetPlayerByName(@NonNull String name,
                                                   @NonNull Pageable pageable) {
        Page<Player> players = service.getPlayerByName(name, pageable);
        if (players.isEmpty()) {
            throw new NotFoundException(messageUtil.getMessage("player.name.not-found", name));
        }
        return players.map(converter::toView);
    }

    public PlayerView handlerCreatePlayer(@NonNull PlayerReq req) {
        final Player player = new Player();

        player.setSurname(req.surname());
        player.setName(req.name());
        player.setPatronymic(req.patronymic());
        player.setBirthdate(req.birthdate());
        player.setNationality(req.nationality());
        player.setPosition(PositionEnum.getPositionByCode(req.position()));
        player.setPlayerNumber(req.playerNumber());
        player.setTeam(null);

        return converter.toView(
                service.savePlayer(player)
        );
    }

    public PlayerView handlerAddToTeam(@NonNull Integer playerId, @NonNull Integer teamId) {
        final Player player = service.getReferenceOrNew(playerId);
        final Team team = teamService.getTeamById(teamId)
                .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("team.id.not-found", teamId)));

        player.setTeam(team);

        return converter.toView(
                service.savePlayer(player)
        );
    }

    public PlayerView handlerUpdatePlayerById(@NonNull Integer id, @NonNull PlayerReq req) {
        final Player prototype = service.getReferenceOrNew(id);

        prototype.setSurname(req.surname());
        prototype.setName(req.name());
        prototype.setPatronymic(req.patronymic());
        prototype.setBirthdate(req.birthdate());
        prototype.setNationality(req.nationality());
        prototype.setPosition(PositionEnum.getPositionByCode(req.position()));
        prototype.setPlayerNumber(req.playerNumber());

        return converter.toView(
                service.savePlayer(prototype)
        );
    }

    public void handlerDeletePlayerById(@NonNull Integer id) {
        service.deletePlayerById(id);
    }
}
