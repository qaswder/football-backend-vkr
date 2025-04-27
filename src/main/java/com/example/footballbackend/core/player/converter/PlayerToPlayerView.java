package com.example.footballbackend.core.player.converter;

import com.example.footballbackend.core.player.dto.Player;
import com.example.footballbackend.core.player.web.contract.PlayerView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PlayerToPlayerView implements Converter<Player, PlayerView> {
    @Override
    public PlayerView convert(Player source) {
        String teamName = "Свободный игрок";
        if (source.getTeam() != null){
            teamName = source.getTeam().getTeamName();
        }

        return new PlayerView(
                source.getId(),
                source.getSurname(),
                source.getName(),
                source.getPatronymic(),
                source.getBirthdate(),
                source.getNationality(),
                source.getPosition().getCode(),
                source.getPlayerNumber(),
                teamName,
                source.getPhotoUrl()
        );
    }
}
