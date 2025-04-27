package com.example.footballbackend.core.result.converter;

import com.example.footballbackend.core.result.dto.Result;
import com.example.footballbackend.core.result.web.contract.ResultView;
import com.example.footballbackend.core.team.dto.Team;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ResultToResultView implements Converter<Result, ResultView> {
    @Override
    public ResultView convert(Result source) {
        Team team = source.getTeam();
        String teamName = team.getTeamName();

        return new ResultView(
                source.getId(),
                source.getTeam().getTeamName(),
                source.getPosition(),
                source.getPlayed(),
                source.getWins(),
                source.getDraws(),
                source.getLosses(),
                source.getPoints(),
                source.getTeam().getId(),
                source.getTournament().getId()
        );
    }
}
