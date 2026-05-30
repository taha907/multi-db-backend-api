package com.gamermatch.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/** Custom Graphics - kuyruk cizimi */
public class QueueCanvas extends Canvas {

    private final List<String> players = new ArrayList<>();

    public QueueCanvas(double width, double height) {
        super(width, height);
        setWidth(width);
        setHeight(height);
    }

    public void setPlayers(List<String> names) {
        players.clear();
        players.addAll(names);
        draw();
    }

    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setFill(Color.web("#1a1a2e"));
        gc.fillRect(0, 0, getWidth(), getHeight());
        gc.setStroke(Color.CYAN);
        gc.strokeRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 12, 12);
        gc.setFill(Color.LIGHTGRAY);
        gc.fillText("Mac Ara Kuyrugu", 20, 35);

        int x = 40, y = 80;
        for (String name : players) {
            gc.setFill(Color.ORANGERED);
            gc.fillOval(x, y, 50, 50);
            gc.setFill(Color.WHITE);
            gc.fillText(name, x + 5, y + 30);
            x += 70;
        }
        if (players.isEmpty()) {
            gc.setFill(Color.GRAY);
            gc.fillText("Kuyruk bos", 40, 100);
        }
    }
}
