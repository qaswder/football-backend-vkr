package com.example.footballbackend.core.tournament.converter;

import com.example.footballbackend.core.tournament.dto.Tournament;
import com.example.footballbackend.core.tournament.web.contract.TournamentView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TournamentConverter {
    private final TournamentToTournamentView toTournamentView;

    public TournamentConverter(TournamentToTournamentView toTournamentView) {
        this.toTournamentView = toTournamentView;
    }

    public TournamentView toView(@NonNull Tournament Tournament) {
        return toTournamentView.convert(Tournament);
    }
}
