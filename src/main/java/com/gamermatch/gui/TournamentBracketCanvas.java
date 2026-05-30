package com.gamermatch.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/** Custom Graphics - turnuva agaci */
public class TournamentBracketCanvas extends Canvas {

    private String t1 = "Takim A", t2 = "Takim B", t3 = "Takim C", t4 = "Takim D";

    public TournamentBracketCanvas(double width, double height) {
        super(width, height);
        setWidth(width);
        setHeight(height);
    }

    public void setTeams(String a, String b, String c, String d) {
        t1 = a; t2 = b; t3 = c; t4 = d;
        draw();
    }

    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setFill(Color.web("#16213e"));
        gc.fillRect(0, 0, getWidth(), getHeight());
        gc.setFill(Color.GOLD);
        gc.fillText("Turnuva Agaci", 20, 30);
        box(gc, 30, 60, t1);
        box(gc, 30, 120, t2);
        box(gc, 30, 200, t3);
        box(gc, 30, 260, t4);
        gc.setStroke(Color.LIME);
        gc.strokeLine(130, 90, 200, 90);
        gc.strokeLine(130, 150, 200, 90);
        gc.strokeLine(130, 230, 200, 230);
        gc.strokeLine(130, 290, 200, 230);
        gc.strokeLine(200, 90, 280, 160);
        gc.strokeLine(200, 230, 280, 160);
        box(gc, 280, 140, "FINAL");
    }

    private void box(GraphicsContext gc, double x, double y, String text) {
        gc.setFill(Color.web("#0f3460"));
        gc.fillRoundRect(x, y, 90, 40, 8, 8);
        gc.setFill(Color.WHITE);
        gc.fillText(text, x + 8, y + 25);
    }
}
