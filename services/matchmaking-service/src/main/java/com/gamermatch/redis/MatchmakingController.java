package com.gamermatch.redis;

import com.gamermatch.common.ApiResponse;
import com.gamermatch.common.PagedList;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/matchmaking")
public class MatchmakingController {

    private final MatchmakingService matchmakingService;

    public MatchmakingController(MatchmakingService matchmakingService) {
        this.matchmakingService = matchmakingService;
    }

    @PostMapping("/join")
    public ApiResponse<QueuePlayer> join(@RequestBody Map<String, String> body) {
        Long playerId = Long.parseLong(body.get("playerId"));
        String game = body.get("game");
        String rank = body.getOrDefault("rank", "UNRANKED");
        return ApiResponse.ok(matchmakingService.joinQueue(playerId, game, rank));
    }

    @DeleteMapping("/leave")
    public ApiResponse<String> leave(@RequestParam Long playerId, @RequestParam String game) {
        matchmakingService.leaveQueue(playerId, game);
        return ApiResponse.ok("Kuyruktan cikildi", "OK");
    }

    @GetMapping("/queue/{game}")
    public ApiResponse<PagedList<QueuePlayer>> queue(@PathVariable String game) {
        return ApiResponse.ok(matchmakingService.getQueue(game));
    }

    @PostMapping("/match/{game}")
    public ApiResponse<LobbyRoom> match(@PathVariable String game) {
        return ApiResponse.ok(matchmakingService.tryMatch(game));
    }
}
