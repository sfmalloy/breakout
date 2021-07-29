package org.example;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class GameAnimation extends AnimationTimer {
    private GraphicsContext context;
    private GameScene scene;

    private GameObject player;
    private GameObject ball;

    private GameObject blockTemplate;
    private GameObject[] blocks;

    private Vector size;
    private double playerSpeed;

    public GameAnimation(GameScene scene, Canvas canvas) {
        this.scene = scene;

        context = canvas.getGraphicsContext2D();
        size = new Vector(canvas.getWidth(), canvas.getHeight());

        player = new GameObject(size.x / 2, size.y - 50, 100, 10, Color.RED);

        ball = new GameObject(0, size.y / 2, 10, 10, Color.BLUE);
        ball.addAttribute("isMovingUp", false);

        blockTemplate = new GameObject();
        blockTemplate.getSize().x = 61;
        blockTemplate.getSize().y = 20;

        int rows = 10;
        int cols = 8;
        blocks = new GameObject[rows * cols];

        int offsetAmount = 20;
        int offset = 0;
        int gapSize = 3;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                blocks[i * cols + j] = new GameObject(gapSize + offset + blockTemplate.getSize().x * j,
                                                   blockTemplate.getSize().y * i,
                                                   blockTemplate.getSize().x - gapSize,
                                                   blockTemplate.getSize().y - gapSize,
                                                   blockTemplate.getColor());
                System.out.println(i * cols + j);
            }

            offset = offset == 0 ? offsetAmount : 0;
        }

        playerSpeed = 5;
    }

    public void handle(long currentTimeNano) {
        // Clear frame
        context.setFill(Color.GRAY);
        context.fillRect(0, 0, size.x, size.y);

        context.setFill(blockTemplate.getColor());
        for (int i = 0; i < blocks.length; ++i) {
            context.fillRect(blocks[i].getPosition().x, blocks[i].getPosition().y, blocks[i].getSize().x, blocks[i].getSize().y);
        }

        movePlayer();
        moveBall();
    }

    private void movePlayer() {
        if (player.isEnabled()) {
            context.setFill(player.getColor());
            context.fillRect(player.getPosition().x, player.getPosition().y, player.getSize().x, player.getSize().y);

            if ((scene.isKeyDown(KeyCode.LEFT) || scene.isKeyDown(KeyCode.A))
                    && player.getPosition().x > 0) {
                player.getPosition().x -= playerSpeed;
            }
            if ((scene.isKeyDown(KeyCode.RIGHT) || scene.isKeyDown(KeyCode.D))
                    && player.getPosition().x < size.x - player.getSize().x) {
                player.getPosition().x += playerSpeed;
            }
        }
    }

    private void moveBall() {
        context.setFill(ball.getColor());
        context.fillOval(ball.getPosition().x, ball.getPosition().y, ball.getSize().x, ball.getSize().y);

//        for (int i = -1; i <= 1; ++i) {
//            for (int j = -1; j <= 1; ++j) {
//                if (!(i == 0 && j == 0)) {
//
//                }
//            }
//        }
    }

    private void checkBallCollision(GameObject gameObject) {
        if (ball.getPosition().x > player.getPosition().x
                && ball.getPosition().y < player.getPosition().y + player.getSize().x
                && ball.getPosition().y > player.getPosition().y - player.getSize().y
                && ball.getPosition().y < player.getPosition().y)
        {
            ball.setAttribute("isMovingUp", true);
            System.out.println("Collision");
        }
    }
}
