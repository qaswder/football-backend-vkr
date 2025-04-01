package com.example.footballbackend.core.statistics.converter;

import com.example.footballbackend.core.statistics.dto.Statistics;
import com.example.footballbackend.core.statistics.web.contract.StatisticsView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StatisticsConverter {
    private final StatisticsToStatisticsView toStatisticsView;

    public StatisticsConverter(StatisticsToStatisticsView toStatisticsView){
        this.toStatisticsView = toStatisticsView;
    }

    public StatisticsView toView(@NonNull Statistics statistics){
        return toStatisticsView.convert(statistics);
    }
}
