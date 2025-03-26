package com.example.footballbackend.core.team.converter;

import com.example.footballbackend.core.team.dto.Team;
import com.example.footballbackend.core.team.web.contract.TeamView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TeamConverter {
    private final TeamToTeamView toTeamView;

    public TeamConverter(TeamToTeamView toTeamView){
        this.toTeamView = toTeamView;
    }

    public TeamView toView(@NonNull Team team){
        return toTeamView.convert(team);
    }
}
