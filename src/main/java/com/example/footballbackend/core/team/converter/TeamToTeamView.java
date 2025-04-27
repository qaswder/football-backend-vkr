package com.example.footballbackend.core.team.converter;

import com.example.footballbackend.core.team.dto.Team;
import com.example.footballbackend.core.team.web.contract.TeamView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeamToTeamView implements Converter<Team, TeamView> {
    @Override
    public TeamView convert(Team source) {
        return new TeamView(
                source.getId(),
                source.getTeamName(),
                source.getTeamShortName(),
                source.getStadium(),
                source.getLogoUrl()
        );
    }
}
