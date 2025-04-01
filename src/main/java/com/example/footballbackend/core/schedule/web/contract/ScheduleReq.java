package com.example.footballbackend.core.schedule.web.contract;

import com.example.footballbackend.util.validation.OnCreate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Создание/изменение расписания")
public record ScheduleReq(
        @NotNull(groups = {OnCreate.class})
        Integer matchId,
        @NotBlank
        String statusMatch
) {
}
