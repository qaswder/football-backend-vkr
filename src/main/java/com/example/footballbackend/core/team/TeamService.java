package com.example.footballbackend.core.team;

import com.example.footballbackend.core.team.dto.Team;
import com.example.footballbackend.core.team.dto.TeamRepo;
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
public class TeamService {
    private final TeamRepo teamRepo;

    public TeamService(TeamRepo teamRepo,
                       MessageUtil messageUtil) {
        this.teamRepo = teamRepo;
    }

    @Transactional(readOnly = true)
    public Page<Team> getAllTeam(Pageable pageable) {
        return teamRepo.findAllTeam(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Team> getTeamById(@NonNull Integer id) {
        return teamRepo.findById(id);
    }

    @Transactional
    public Team saveTeam(@NonNull Team team) {
        try {
            return teamRepo.save(team);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Team getReferenceOrNew(@Nullable Integer id) {
        return id == null ? new Team() : teamRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteTeamById(@NonNull Integer id) {
        teamRepo.deleteById(id);
    }
}
