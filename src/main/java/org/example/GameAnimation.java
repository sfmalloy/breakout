package org.example;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GameAnimation extends AnimationTimer {
    private GraphicsContext context;
    private GameScene scene;

    private Player player;

    private double width;
    private double height;
    private double playerSpeed;

    public GameAnimation(GameScene scene, Canvas canvas) {
        this.scene = scene;

        this.context = canvas.getGraphicsContext2D();
        this.width = canvas.getWidth();
        this.height = canvas.getHeight();

        this.player = new Player(width / 2, height / 2, 20, 20, Color.RED);

        this.playerSpeed = 5;
    }

    public void handle(long currentTimeNano) {
        context.clearRect(0, 0, width, height);

        context.setFill(player.getColor());
        context.fillRect(player.getXPos(), player.getYPos(), player.getWidth(), player.getHeight());

        if (scene.isKeyDown(KeyCode.LEFT)) {
            player.moveLeft(playerSpeed);
        }
        if (scene.isKeyDown(KeyCode.RIGHT)) {
            player.moveRight(playerSpeed);
        }
        if (scene.isKeyDown(KeyCode.UP)) {
            player.moveUp(playerSpeed);
        }
        if (scene.isKeyDown(KeyCode.DOWN)) {
            player.moveDown(playerSpeed);
        }
    }
}

//        long startTimeNano = System.nanoTime();
//        AnimationTimer anim = new AnimationTimer() {
//            @Override
//            public void handle(long currentTimeNano) {
//                double dt = (currentTimeNano - startTimeNano) * NANOSECONDS_TO_SECONDS;
//
//                context.clearRect(0, 0, 512, 512);
//                context.setFill(Color.RED);
//                context.fillOval(dt, dt, 20, 20);
//            }
//        };
//        anim.start();