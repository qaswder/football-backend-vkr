package com.example.footballbackend.core.result.web;

import com.example.footballbackend.core.result.handler.ResultHandler;
import com.example.footballbackend.core.result.web.contract.ResultReq;
import com.example.footballbackend.core.result.web.contract.ResultView;
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
@RequestMapping("/api/result")
@Tag(name = "Турнирная таблица")
public class ResultController {
    private final ResultHandler handler;

    public ResultController(ResultHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение таблицы по id")
    public ResultView getResultById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetResultById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всех таблиц")
    public Page<ResultView> getAllResults(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllResult(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Создание таблицы")
    public ResultView createResult(@Valid @RequestBody @NotNull ResultReq req) {
        return handler.handlerCreateResult(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение таблицы")
    public ResultView updateResultById(@PathVariable @NotNull Integer id,
                                       @Valid @RequestBody @NotNull ResultReq req) {
        return handler.handlerUpdateResultById(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление таблицы")
    public void deleteResultById(@PathVariable @NotNull Integer id) {
        handler.handlerDeleteResultById(id);
    }
}
