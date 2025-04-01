package com.example.footballbackend.core.schedule.web.contract;

import com.example.footballbackend.core.match.web.contract.MatchView;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Расписание")
public record ScheduleView(
        @Schema(description = "Идентификатор")
        Integer id,
        @Schema(description = "Матч")
        MatchView matchView,
        @Schema(description = "Статус сатча")
        String statusMatch
) {
}
