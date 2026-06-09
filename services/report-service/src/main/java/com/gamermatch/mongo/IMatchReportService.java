package com.gamermatch.mongo;

import com.gamermatch.common.PagedList;

import java.util.Map;

public interface IMatchReportService {
    MatchReport save(String matchId, String game, Long winnerId, Map<String, Object> stats);
    MatchReport getById(String id);
    PagedList<MatchReport> getByGame(String game);
}
