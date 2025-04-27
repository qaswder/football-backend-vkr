package com.example.footballbackend.core.tournament;

import com.example.footballbackend.core.tournament.dto.Tournament;
import com.example.footballbackend.core.tournament.dto.TournamentRepo;
import com.example.footballbackend.error.ConflictResourceException;
import com.example.footballbackend.util.MessageUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TournamentService {
    private final TournamentRepo tournamentRepo;

    public TournamentService(TournamentRepo tournamentRepo,
                       MessageUtil messageUtil) {
        this.tournamentRepo = tournamentRepo;
    }

    @Transactional(readOnly = true)
    public Page<Tournament> getAllTournament(Pageable pageable) {
        return tournamentRepo.findAllTournament(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Tournament> getTournamentById(@NonNull Integer id) {
        return tournamentRepo.findById(id);
    }

    @Transactional
    public Tournament saveTournament(@NonNull Tournament tournament) {
        try {
            return tournamentRepo.save(tournament);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Tournament getReferenceOrNew(@Nullable Integer id) {
        return id == null ? new Tournament() : tournamentRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteTournamentById(@NonNull Integer id) {
        tournamentRepo.deleteById(id);
    }
}
