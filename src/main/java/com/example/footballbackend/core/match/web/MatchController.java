package com.example.footballbackend.core.match.web;

import com.example.footballbackend.core.match.handler.MatchHandler;
import com.example.footballbackend.core.match.web.contract.MatchReq;
import com.example.footballbackend.core.match.web.contract.MatchView;
import com.example.footballbackend.util.validation.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
@Tag(name = "Матч")
public class MatchController {
    private final MatchHandler handler;

    public MatchController(MatchHandler handler){
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение матча по id")
    public MatchView getMatchById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetMatchById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всех матчей")
    public Page<MatchView> getAllMatch(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllMatch(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    @Operation(summary = "Создание матча")
    public MatchView createMatch(@Validated(OnCreate.class) @RequestBody @NotNull MatchReq req) {
        return handler.handlerCreateMatch(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение матча")
    public MatchView updateMatchById(@PathVariable @NotNull Integer id,
                                     @Valid @RequestBody @NotNull MatchReq req) {
        return handler.handlerUpdateMatchById(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление матча")
    public void deleteMatchById(@PathVariable @NotNull Integer id) {
        handler.handlerDeleteMatchById(id);
    }
}
