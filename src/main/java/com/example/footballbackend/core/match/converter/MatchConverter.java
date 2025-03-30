package com.example.footballbackend.core.match.converter;

import com.example.footballbackend.core.match.dto.Match;
import com.example.footballbackend.core.match.web.contract.MatchView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class MatchConverter {
    private final MatchToMatchView toMatchView;

    public MatchConverter(MatchToMatchView toMatchView){
        this.toMatchView = toMatchView;
    }

    public MatchView toView(@NonNull Match match){
        return toMatchView.convert(match);
    }
}
