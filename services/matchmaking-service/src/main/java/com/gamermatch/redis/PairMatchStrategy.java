package com.gamermatch.redis;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PairMatchStrategy implements IMatchStrategy {

    @Override
    public LobbyRoom createMatch(String game, MatchQueueRepository repo, QueuePlayer p1, QueuePlayer p2) {
        LobbyRoom lobby = new LobbyRoom();
        lobby.setLobbyId(UUID.randomUUID().toString());
        lobby.setGame(game);
        lobby.getPlayerIds().add(p1.getPlayerId());
        lobby.getPlayerIds().add(p2.getPlayerId());
        String data = lobby.getLobbyId() + "|" + game + "|" + p1.getPlayerId() + "," + p2.getPlayerId();
        repo.saveLobby(lobby.getLobbyId(), data);
        return lobby;
    }
}
