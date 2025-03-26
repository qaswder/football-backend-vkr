package com.example.footballbackend.core.coach.converter;

import com.example.footballbackend.core.coach.dto.Coach;
import com.example.footballbackend.core.coach.web.contract.CoachView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CoachToCoachView implements Converter<Coach, CoachView> {
    @Override
    public CoachView convert(Coach source) {
        return new CoachView(
                source.getId(),
                source.getSurname(),
                source.getName(),
                source.getPatronymic()
        );
    }
}
