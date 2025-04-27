package com.example.footballbackend.core.statistics;

import com.example.footballbackend.core.statistics.dto.Statistics;
import com.example.footballbackend.core.statistics.dto.StatisticsRepo;
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
public class StatisticsService {
    private final StatisticsRepo statisticsRepo;
    private final MessageUtil messageUtil;

    public StatisticsService(StatisticsRepo statisticsRepo,
                             MessageUtil messageUtil){
        this.statisticsRepo = statisticsRepo;
        this.messageUtil = messageUtil;
    }

    @Transactional(readOnly = true)
    public Page<Statistics> getAllStatistics(Pageable pageable) {
        return statisticsRepo.findAllStatistics(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Statistics> getStatisticsById(@NonNull Integer id) {
        return statisticsRepo.findById(id);
    }

    @Transactional
    public Statistics saveStatistics(@NonNull Statistics match) {
        try {
            return statisticsRepo.save(match);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictResourceException(e.getMessage());
        }
    }

    @Transactional
    public Statistics getReferenceOrNew(@Nullable Integer id) {
        return id == null ? new Statistics() : statisticsRepo.getReferenceById(id);
    }

    @Transactional
    public void deleteStatisticsById(@NonNull Integer id) {
        statisticsRepo.deleteById(id);
    }
}
