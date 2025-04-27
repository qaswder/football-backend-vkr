package com.example.footballbackend.core.tournament.web;

import com.example.footballbackend.core.tournament.handler.TournamentHandler;
import com.example.footballbackend.core.tournament.web.contract.TournamentReq;
import com.example.footballbackend.core.tournament.web.contract.TournamentView;
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
@RequestMapping("/api/tournament")
@Tag(name = "Турнир")
public class TournamentController {
    private final TournamentHandler handler;

    public TournamentController(TournamentHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение турнира по id")
    public TournamentView getTournamentById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetTournamentById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всех турниров")
    public Page<TournamentView> getAllTournaments(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                                  @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllTournament(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Создание турнира")
    public TournamentView createTournament(@Valid @RequestBody @NotNull TournamentReq req) {
        return handler.handlerCreateTournament(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение турнира")
    public TournamentView updateTournamentById(@PathVariable @NotNull Integer id,
                                               @Valid @RequestBody @NotNull TournamentReq req) {
        return handler.handlerUpdateTournamentById(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление турнира")
    public void deleteTournamentById(@PathVariable @NotNull Integer id) {
        handler.handlerDeleteTournamentById(id);
    }
}
