package com.example.footballbackend.core.tournament.handler;

import com.example.footballbackend.core.tournament.TournamentService;
import com.example.footballbackend.core.tournament.converter.TournamentConverter;
import com.example.footballbackend.core.tournament.dto.Tournament;
import com.example.footballbackend.core.tournament.web.contract.TournamentReq;
import com.example.footballbackend.core.tournament.web.contract.TournamentView;
import com.example.footballbackend.error.NotFoundException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TournamentHandler {
    private final TournamentConverter converter;
    private final TournamentService service;
    private final MessageUtil messageUtil;

    public TournamentHandler(TournamentConverter converter,
                             TournamentService service,
                             MessageUtil messageUtil) {
        this.converter = converter;
        this.service = service;
        this.messageUtil = messageUtil;
    }

    public TournamentView handlerGetTournamentById(@NonNull Integer id) {
        return converter.toView(
                service.getTournamentById(id)
                        .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("tournament.id.not-found", id)))
        );
    }

    public Page<TournamentView> handlerGetAllTournament(@NonNull Pageable pageable) {
        Page<Tournament> tournaments = service.getAllTournament(pageable);
        List<TournamentView> tournamentViewList = tournaments.stream()
                .map(converter::toView)
                .toList();
        return new PageImpl<>(tournamentViewList);
    }

    public TournamentView handlerCreateTournament(@NonNull TournamentReq req) {
        final Tournament tournament = new Tournament();

        tournament.setTournamentName(req.tournamentName());
        tournament.setSeason(req.season());

        return converter.toView(
                service.saveTournament(tournament)
        );
    }

    public TournamentView handlerUpdateTournamentById(@NonNull Integer id, @NonNull TournamentReq req) {
        final Tournament prototype = service.getReferenceOrNew(id);

        prototype.setTournamentName(req.tournamentName());
        prototype.setSeason(req.season());

        return converter.toView(
                service.saveTournament(prototype)
        );
    }

    public void handlerDeleteTournamentById(@NonNull Integer id) {
        service.deleteTournamentById(id);
    }

}
