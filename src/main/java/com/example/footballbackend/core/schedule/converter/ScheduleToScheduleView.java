package com.example.footballbackend.core.schedule.converter;

import com.example.footballbackend.core.match.converter.MatchConverter;
import com.example.footballbackend.core.match.web.contract.MatchView;
import com.example.footballbackend.core.schedule.dto.Schedule;
import com.example.footballbackend.core.schedule.web.contract.ScheduleView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScheduleToScheduleView implements Converter<Schedule, ScheduleView> {
    private final MatchConverter matchConverter;

    public ScheduleToScheduleView(MatchConverter matchConverter){
        this.matchConverter = matchConverter;
    }
    @Override
    public ScheduleView convert(Schedule source) {
        MatchView matchView = matchConverter.toView(source.getMatch());

        return new ScheduleView(
                source.getId(),
                matchView,
                source.getStatusMatch()
        );
    }
}
