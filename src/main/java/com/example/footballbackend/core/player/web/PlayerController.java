package com.example.footballbackend.core.player.web;

import com.example.footballbackend.core.player.handler.PlayerHandler;
import com.example.footballbackend.core.player.web.contract.PlayerReq;
import com.example.footballbackend.core.player.web.contract.PlayerView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@Tag(name = "Игрок")
public class PlayerController {
    private final PlayerHandler handler;

    public PlayerController(PlayerHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение игрока по id")
    public PlayerView getPlayerById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetPlayerById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всех игроков")
    public Page<PlayerView> getAllPlayers(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllPlayer(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search-name")
    @Operation(summary = "Получение игрока по имени")
    public Page<PlayerView> getPlayerByName(@RequestParam @NotNull String name,
                                            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetPlayerByName(name, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    @Operation(summary = "Создание игрока")
    public PlayerView createPlayer(@Valid @RequestBody @NotNull PlayerReq req) {
        return handler.handlerCreatePlayer(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение игрока")
    public PlayerView updatePlayerById(@PathVariable @NotNull Integer id,
                                       @Valid @RequestBody @NotNull PlayerReq req) {
        return handler.handlerUpdatePlayerById(id, req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/add-team")
    @Operation(summary = "Добавление команды игроку")
    public PlayerView addTeamToPlayerById(@PathVariable @NotNull Integer id,
                                          @Valid @RequestBody @NotNull Integer teamId) {
        return handler.handlerAddToTeam(id, teamId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление игрока")
    public void deletePlayerById(@PathVariable @NotNull Integer id) {
        handler.handlerDeletePlayerById(id);
    }
}
