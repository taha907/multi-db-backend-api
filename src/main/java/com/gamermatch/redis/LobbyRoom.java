package com.gamermatch.redis;

import java.util.ArrayList;
import java.util.List;

public class LobbyRoom {

    private String lobbyId;
    private String game;
    private List<Long> playerIds = new ArrayList<>();

    public String getLobbyId() { return lobbyId; }
    public void setLobbyId(String lobbyId) { this.lobbyId = lobbyId; }
    public String getGame() { return game; }
    public void setGame(String game) { this.game = game; }
    public List<Long> getPlayerIds() { return playerIds; }
}
