package com.example.footballbackend.core.team.web;

import com.example.footballbackend.core.team.handler.TeamHandler;
import com.example.footballbackend.core.team.web.contract.TeamReq;
import com.example.footballbackend.core.team.web.contract.TeamView;
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
@RequestMapping("/team")
@Tag(name = "Команда")
public class TeamController {
    private final TeamHandler handler;

    public TeamController(TeamHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение игрока по id")
    public TeamView getTeamById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetTeamById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всех игроков")
    public Page<TeamView> getAllTeams(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllTeam(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    @Operation(summary = "Создание тренера")
    public TeamView createTeam(@Valid @RequestBody @NotNull TeamReq req) {
        return handler.handlerCreateTeam(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение тренера")
    public TeamView updateTeamById(@PathVariable @NotNull Integer id,
                                   @Valid @RequestBody @NotNull TeamReq req) {
        return handler.handlerUpdateTeamById(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление тренера")
    public void deleteTeamById(@PathVariable @NotNull Integer id) {
        handler.handlerDeleteTeamById(id);
    }
}
