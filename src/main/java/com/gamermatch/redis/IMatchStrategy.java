package com.gamermatch.redis;

public interface IMatchStrategy {
    LobbyRoom createMatch(String game, MatchQueueRepository repo, QueuePlayer p1, QueuePlayer p2);
}
