package com.example.footballbackend.core.statistics.converter;

import com.example.footballbackend.core.player.dto.Player;
import com.example.footballbackend.core.statistics.dto.Statistics;
import com.example.footballbackend.core.statistics.web.contract.StatisticsView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StatisticsToStatisticsView implements Converter<Statistics, StatisticsView> {
    @Override
    public StatisticsView convert(Statistics source) {
        Player player = source.getPlayer();
        String fullName =
                player.getSurname() + " " +
                player.getName() + " " +
                player.getPatronymic();

        return new StatisticsView(
                source.getId(),
                player.getPosition().getCode(),
                fullName,
                source.getGoals(),
                source.getAssists(),
                source.getYellowCards(),
                source.getRedCards(),
                source.getSeason()
        );
    }
}
