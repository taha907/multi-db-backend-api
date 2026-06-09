package com.gamermatch.jdbc;

import jakarta.validation.constraints.NotBlank;

public class TournamentRequest {
    @NotBlank private String name;
    @NotBlank private String game;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGame() { return game; }
    public void setGame(String game) { this.game = game; }
}
