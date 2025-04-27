package com.example.footballbackend.core.result.web.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.NonNull;

@Schema(description = "Создание/изменение турнира")
public record ResultReq(
        @NonNull
        Integer position,
        @NonNull
        Integer played,
        @NonNull
        Integer wins,
        @NonNull
        Integer draws,
        @NonNull
        Integer losses,
        @NonNull
        Integer points,
        @NonNull
        Integer teamId,
        @NonNull
        Integer tournamentId
) {
}
