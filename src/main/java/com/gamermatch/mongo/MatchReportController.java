package com.gamermatch.mongo;

import com.gamermatch.common.ApiResponse;
import com.gamermatch.common.PagedList;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/match-reports")
public class MatchReportController {

    private final IMatchReportService matchReportService;

    public MatchReportController(IMatchReportService matchReportService) {
        this.matchReportService = matchReportService;
    }

    @PostMapping
    public ApiResponse<MatchReport> create(@RequestBody Map<String, Object> body) {
        String matchId = (String) body.get("matchId");
        String game = (String) body.get("game");
        Long winnerId = Long.valueOf(body.get("winnerId").toString());
        @SuppressWarnings("unchecked")
        Map<String, Object> stats = (Map<String, Object>) body.get("playerStats");
        return ApiResponse.ok(matchReportService.save(matchId, game, winnerId, stats));
    }

    @GetMapping("/{id}")
    public ApiResponse<MatchReport> getById(@PathVariable String id) {
        return ApiResponse.ok(matchReportService.getById(id));
    }

    @GetMapping("/game/{game}")
    public ApiResponse<PagedList<MatchReport>> byGame(@PathVariable String game) {
        return ApiResponse.ok(matchReportService.getByGame(game));
    }
}
