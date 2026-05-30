package com.gamermatch.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class MatchQueueRepository {

    private static final String QUEUE = "queue:";
    private static final String LOBBY = "lobby:";
    private final StringRedisTemplate redis;

    public MatchQueueRepository(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public void addToQueue(QueuePlayer player) {
        redis.opsForList().rightPush(QUEUE + player.getGame(), player.toRedisValue());
    }

    public List<QueuePlayer> getQueue(String game) {
        List<String> raw = redis.opsForList().range(QUEUE + game, 0, -1);
        List<QueuePlayer> list = new ArrayList<>();
        if (raw != null) {
            for (String s : raw) {
                list.add(QueuePlayer.fromRedisValue(s));
            }
        }
        return list;
    }

    public QueuePlayer popFromQueue(String game) {
        String value = redis.opsForList().leftPop(QUEUE + game);
        return value == null ? null : QueuePlayer.fromRedisValue(value);
    }

    public void removePlayer(String game, Long playerId) {
        String key = QUEUE + game;
        List<String> raw = redis.opsForList().range(key, 0, -1);
        if (raw == null) return;
        redis.delete(key);
        for (String s : raw) {
            if (!QueuePlayer.fromRedisValue(s).getPlayerId().equals(playerId)) {
                redis.opsForList().rightPush(key, s);
            }
        }
    }

    public void saveLobby(String lobbyId, String data) {
        redis.opsForValue().set(LOBBY + lobbyId, data, 30, TimeUnit.MINUTES);
    }
}
