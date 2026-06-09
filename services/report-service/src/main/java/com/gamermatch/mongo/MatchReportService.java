package com.gamermatch.mongo;

import com.gamermatch.common.PagedList;
import com.gamermatch.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class MatchReportService implements IMatchReportService {

    private final MatchReportRepository repository;

    public MatchReportService(MatchReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public MatchReport save(String matchId, String game, Long winnerId, Map<String, Object> stats) {
        MatchReport report = new MatchReport();
        report.setMatchId(matchId);
        report.setGame(game);
        report.setWinnerId(winnerId);
        report.setPlayerStats(stats);
        report.setPlayedAt(LocalDateTime.now());
        return repository.save(report);
    }

    @Override
    public MatchReport getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mac raporu bulunamadi: " + id));
    }

    @Override
    public PagedList<MatchReport> getByGame(String game) {
        return new PagedList<>(repository.findByGame(game));
    }
}
