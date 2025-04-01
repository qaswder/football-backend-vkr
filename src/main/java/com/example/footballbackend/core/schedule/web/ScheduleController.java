package com.example.footballbackend.core.schedule.web;

import com.example.footballbackend.core.schedule.handler.ScheduleHandler;
import com.example.footballbackend.core.schedule.web.contract.ScheduleReq;
import com.example.footballbackend.core.schedule.web.contract.ScheduleView;
import com.example.footballbackend.util.validation.OnCreate;
import com.example.footballbackend.util.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
@Tag(name = "Расписание матчей")
public class ScheduleController {

    private final ScheduleHandler handler;

    public ScheduleController(ScheduleHandler handler) {
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение расписания по id")
    public ScheduleView getScheduleById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetScheduleById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всего расписания")
    public Page<ScheduleView> getAllSchedule(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                             @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllSchedule(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    @Operation(summary = "Создание расписания")
    public ScheduleView createSchedule(@Validated(OnCreate.class) @RequestBody @NotNull ScheduleReq req) {
        return handler.handlerCreateSchedule(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение расписания")
    public ScheduleView updateScheduleById(@PathVariable @NotNull Integer id,
                                           @Validated(OnUpdate.class) @RequestBody @NotNull ScheduleReq req) {
        return handler.handlerUpdateScheduleById(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление расписания")
    public void deleteScheduleById(@PathVariable @NotNull Integer id) {
        handler.handlerDeleteScheduleById(id);
    }
}
