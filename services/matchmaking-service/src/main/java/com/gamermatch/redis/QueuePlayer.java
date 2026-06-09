package com.gamermatch.redis;

public class QueuePlayer {

    private Long playerId;
    private String game;
    private String rank;

    public QueuePlayer() {
    }

    public QueuePlayer(Long playerId, String game, String rank) {
        this.playerId = playerId;
        this.game = game;
        this.rank = rank;
    }

    public Long getPlayerId() { return playerId; }
    public String getGame() { return game; }
    public String getRank() { return rank; }

    public String toRedisValue() {
        return playerId + "|" + game + "|" + rank;
    }

    public static QueuePlayer fromRedisValue(String value) {
        String[] p = value.split("\\|");
        return new QueuePlayer(Long.parseLong(p[0]), p[1], p[2]);
    }
}
