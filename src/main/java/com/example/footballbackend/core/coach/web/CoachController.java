package com.example.footballbackend.core.coach.web;

import com.example.footballbackend.core.coach.handler.CoachHandler;
import com.example.footballbackend.core.coach.web.contract.CoachReq;
import com.example.footballbackend.core.coach.web.contract.CoachView;
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
@RequestMapping("/coach")
@Tag(name = "Тренер")
public class CoachController {
    private final CoachHandler handler;

    public CoachController(CoachHandler handler){
        this.handler = handler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Получение тренера по id")
    public CoachView getCoachById(@PathVariable @NotNull Integer id) {
        return handler.handlerGetCoachById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Получение всех тренеров")
    public Page<CoachView> getAllCoach(@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return handler.handlerGetAllCoach(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    @Operation(summary = "Создание тренера")
    public CoachView createCoach(@Valid @RequestBody @NotNull CoachReq req) {
        return handler.handlerCreateCoach(req);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение тренера")
    public CoachView updateCoachById(@PathVariable @NotNull Integer id,
                                       @Valid @RequestBody @NotNull CoachReq req) {
        return handler.handlerUpdateCoachById(id, req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление тренера")
    public void deleteCoachById(@PathVariable @NotNull Integer id) {
        handler.handlerDeleteCoachById(id);
    }
}
