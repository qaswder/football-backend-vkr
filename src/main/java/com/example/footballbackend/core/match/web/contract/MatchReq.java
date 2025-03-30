package com.example.footballbackend.core.match.web.contract;

import com.example.footballbackend.util.validation.OnCreate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "Создание/редактирование матча")
public record MatchReq(
        @NotNull(groups = {OnCreate.class})
        Integer teamHomeId,
        @NotNull(groups = {OnCreate.class})
        Integer teamAwayId,
        @NotBlank
        String score,
        @NotNull
        LocalDateTime date
) {
}
