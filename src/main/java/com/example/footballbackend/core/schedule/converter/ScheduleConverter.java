package com.example.footballbackend.core.schedule.converter;

import com.example.footballbackend.core.schedule.dto.Schedule;
import com.example.footballbackend.core.schedule.web.contract.ScheduleView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverter {
    private final ScheduleToScheduleView toScheduleView;

    public ScheduleConverter(ScheduleToScheduleView toScheduleView){
        this.toScheduleView = toScheduleView;
    }

    public ScheduleView toView(@NonNull Schedule schedule){
        return toScheduleView.convert(schedule);
    }
}
