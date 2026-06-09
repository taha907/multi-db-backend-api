package com.gamermatch.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "match_reports")
public class MatchReport {

    @Id
    private String id;
    private String matchId;
    private String game;
    private Long winnerId;
    private LocalDateTime playedAt;
    private Map<String, Object> playerStats;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMatchId() { return matchId; }
    public void setMatchId(String matchId) { this.matchId = matchId; }
    public String getGame() { return game; }
    public void setGame(String game) { this.game = game; }
    public Long getWinnerId() { return winnerId; }
    public void setWinnerId(Long winnerId) { this.winnerId = winnerId; }
    public LocalDateTime getPlayedAt() { return playedAt; }
    public void setPlayedAt(LocalDateTime playedAt) { this.playedAt = playedAt; }
    public Map<String, Object> getPlayerStats() { return playerStats; }
    public void setPlayerStats(Map<String, Object> playerStats) { this.playerStats = playerStats; }
}
