package com.example.footballbackend.core.player.converter;

import com.example.footballbackend.core.player.dto.Player;
import com.example.footballbackend.core.player.web.contract.PlayerView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PlayerConverter {
    private final PlayerToPlayerView toPlayerView;

    public PlayerConverter(PlayerToPlayerView toPlayerView){
        this.toPlayerView = toPlayerView;
    }

    public PlayerView toView(@NonNull Player player){
        return toPlayerView.convert(player);
    }
}
