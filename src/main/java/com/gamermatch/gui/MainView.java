package com.gamermatch.gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;


public class MainView {

    private final BorderPane root = new BorderPane();
    private final ApiClient api = new ApiClient();
    private final TextArea logArea = new TextArea();
    private final QueueCanvas queueCanvas = new QueueCanvas(400, 220);
    private final TournamentBracketCanvas bracketCanvas = new TournamentBracketCanvas(400, 320);

    public MainView() {
        root.setPadding(new Insets(10));
        TabPane tabs = new TabPane();
        tabs.getTabs().add(new Tab("Kullanicilar", buildUserTab()));
        tabs.getTabs().add(new Tab("Mac Ara", buildMatchTab()));
        tabs.getTabs().add(new Tab("Turnuva", buildTournamentTab()));
        root.setCenter(tabs);
        logArea.setEditable(false);
        logArea.setPrefRowCount(6);
        root.setBottom(new VBox(4, new Label("API Log:"), logArea));
    }

    public BorderPane getRoot() { return root; }

    private VBox buildUserTab() {
        TextField username = new TextField();
        username.setPromptText("kullanici adi");
        TextField email = new TextField();
        email.setPromptText("email");
        Button kaydet = new Button("Kaydet");
        Button listele = new Button("Listele");
        kaydet.setOnAction(e -> {
            String json = "{\"username\":\"" + username.getText() + "\",\"email\":\"" + email.getText()
                    + "\",\"password\":\"1234\",\"gameRank\":\"Gold\"}";
            runApi(() -> api.postJson("/api/users", json));
        });
        listele.setOnAction(e -> runApi(() -> api.get("/api/users")));
        return new VBox(8, new Label("Kullanici"), username, email, new HBox(8, kaydet, listele));
    }

    private VBox buildMatchTab() {
        TextField playerId = new TextField("1");
        TextField game = new TextField("VALORANT");
        Button join = new Button("Kuyruga Gir");
        Button match = new Button("Eslestir");
        Button refresh = new Button("Kuyrugu Ciz");
        join.setOnAction(e -> {
            String json = "{\"playerId\":\"" + playerId.getText() + "\",\"game\":\"" + game.getText() + "\",\"rank\":\"Gold\"}";
            runApi(() -> api.postJson("/api/matchmaking/join", json));
        });
        match.setOnAction(e -> runApi(() -> api.post("/api/matchmaking/match/" + game.getText())));
        refresh.setOnAction(e -> queueCanvas.setPlayers(Arrays.asList("P" + playerId.getText(), "P2", "P3")));
        HBox row = new HBox(20,
                new VBox(8, new Label("Matchmaking"), playerId, game, new HBox(8, join, match, refresh)),
                new VBox(new Label("Ozel cizim"), queueCanvas));
        row.setPadding(new Insets(10));
        return new VBox(row);
    }

    private VBox buildTournamentTab() {
        TextField t1 = new TextField("Dragon");
        TextField t2 = new TextField("Phoenix");
        TextField t3 = new TextField("Wolf");
        TextField t4 = new TextField("Storm");
        Button ciz = new Button("Agaci Guncelle");
        Button apiList = new Button("Turnuvalari Getir");
        ciz.setOnAction(e -> bracketCanvas.setTeams(t1.getText(), t2.getText(), t3.getText(), t4.getText()));
        apiList.setOnAction(e -> runApi(() -> api.get("/api/tournaments")));
        bracketCanvas.draw();
        HBox row = new HBox(20,
                new VBox(8, new Label("Turnuva"), t1, t2, t3, t4, new HBox(8, ciz, apiList)),
                bracketCanvas);
        row.setPadding(new Insets(10));
        return new VBox(row);
    }

    private void runApi(ApiTask task) {
        try {
            logArea.appendText(task.run() + "\n");
        } catch (Exception ex) {
            logArea.appendText("Hata: " + ex.getMessage() + "\n");
        }
    }

    @FunctionalInterface
    private interface ApiTask {
        String run() throws Exception;
    }
}
