package com.gamermatch.jdbc;

public class Tournament {
    private Long id;
    private String name;
    private String game;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGame() { return game; }
    public void setGame(String game) { this.game = game; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
