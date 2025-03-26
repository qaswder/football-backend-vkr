package com.example.footballbackend.core.coach.converter;

import com.example.footballbackend.core.coach.dto.Coach;
import com.example.footballbackend.core.coach.web.contract.CoachView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CoachConverter {
    private final CoachToCoachView toCoachView;

    public CoachConverter(CoachToCoachView toCoachView){
        this.toCoachView = toCoachView;
    }

    public CoachView toView(@NonNull Coach coach){
        return toCoachView.convert(coach);
    }
}
