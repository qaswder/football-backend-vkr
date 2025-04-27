package com.example.footballbackend.core.tournament.converter;

import com.example.footballbackend.core.tournament.dto.Tournament;
import com.example.footballbackend.core.tournament.web.contract.TournamentView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TournamentToTournamentView implements Converter<Tournament, TournamentView> {
    @Override
    public TournamentView convert(Tournament source) {
        return new TournamentView(
                source.getId(),
                source.getTournamentName(),
                source.getSeason()
        );
    }
}
