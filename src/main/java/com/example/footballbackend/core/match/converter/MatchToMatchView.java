package com.example.footballbackend.core.match.converter;

import com.example.footballbackend.core.match.dto.Match;
import com.example.footballbackend.core.match.web.contract.MatchView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MatchToMatchView implements Converter<Match, MatchView> {
    @Override
    public MatchView convert(Match source) {
        return new MatchView(
                source.getId(),
                source.getHomeTeam().getTeamName(),
                source.getAwayTeam().getTeamName(),
                source.getScore(),
                source.getDateTime()
        );
    }
}
