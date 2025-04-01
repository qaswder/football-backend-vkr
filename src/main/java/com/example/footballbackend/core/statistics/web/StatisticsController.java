package com.example.footballbackend.core.statistics.web;

import com.example.footballbackend.core.statistics.handler.StatisticsHandler;
import com.example.footballbackend.core.statistics.web.contract.StatisticsReq;
import com.example.footballbackend.core.statistics.web.contract.StatisticsView;
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
@RequestMapping("/stats")
@Tag(name = "Статистика")
public class StatisticsController {
    private final StatisticsHandler handler;

    public StatisticsController(StatisticsHandler handler){
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение статистики игрока по id")
    public StatisticsView getStatisticsById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetStatisticsById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение статистики всех игроков")
    public Page<StatisticsView> getAllStatistics(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllStatistics(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    @Operation(summary = "Создание статистики игрока")
    public StatisticsView createStatistics(@Validated(OnCreate.class) @RequestBody @NotNull StatisticsReq req) {
        return handler.handlerCreateStatistics(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/edit/{id}")
    @Operation(summary = "Изменение статистики игрока")
    public StatisticsView updateStatisticsById(@PathVariable @NotNull Integer id,
                                          @Valid @RequestBody @NotNull StatisticsReq req) {
        return handler.handlerUpdateStatisticsById(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление статистики игрока")
    public void deleteStatisticsById(@PathVariable @NotNull Integer id) {
        handler.handlerDeleteStatisticsById(id);
    }
}
