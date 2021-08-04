package org.example;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class GameAnimation extends AnimationTimer {
    private GameScene scene;
    private int rows;
    private int cols;

    private GraphicsContext context;

    private GameObject player;
    private GameObject ball;

    private GameObject blockTemplate;
    private ArrayList<GameObject> blocks;

    private double EPSILON = 0.000001;

    private Vector size;
    private double playerSpeed;

    public GameAnimation(GameScene scene, Canvas canvas, int rows, int cols) {
        this.scene = scene;
        this.rows = rows;
        this.cols = cols;

        context = canvas.getGraphicsContext2D();
        size = new Vector(canvas.getWidth(), canvas.getHeight());

        initPlayer();
        initBall();
        initBlocks();
    }

    /**
     * AnimationTimer handle method that draws to the window every frame.
     * 
     * @param currentTimeNano the current time in nanoseconds. Here, I am ignoring it completely.
     * @post A new frame is drawn to the screen on top of the old one.
     */
    @Override
    public void handle(long currentTimeNano) {
        // Clear frame
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, size.x, size.y);

        context.setFill(blockTemplate.getColor());
        for (int i = 0; i < blocks.size(); ++i) {
            if (blocks.get(i).isEnabled()) {
                context.fillRect(blocks.get(i).getPosition().x,
                blocks.get(i).getPosition().y,
                blocks.get(i).getSize().x,
                blocks.get(i).getSize().y);
            }
        }

        movePlayer();
        moveBall();
    }

    /**
     * @post A newly constructed player is initialized and placed in the bottom center of the window
    */
    private void initPlayer() {
        double width = 120;
        double height = 30;
        
        double xPos = (size.x / 2) - (width / 2);
        double yPos = size.y - 50;

        player = new GameObject(xPos, yPos, width, height, Color.GRAY);
        player.setAttribute("ballCollide", false);
        playerSpeed = 5;
    }

    private void initBlocks() {
        blocks = new ArrayList<>();

        int startOffset = 10;
        int gapSize = 4;

        blockTemplate = new GameObject(0, 0, (size.x - 2 * startOffset) / cols, size.y / (2 * rows), Color.WHITE);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                blocks.add(new GameObject(blockTemplate.getSize().x * j + startOffset + gapSize / 2,
                                          blockTemplate.getSize().y * i + startOffset + gapSize / 2,
                                          blockTemplate.getSize().x - gapSize,
                                          blockTemplate.getSize().y - gapSize,
                                          blockTemplate.getColor()));
            }
            ++i;
            if (i < rows) {
                for (int j = 0; j < cols - 1; ++j) {
                    blocks.add(new GameObject(blockTemplate.getSize().x * j + blockTemplate.getSize().x / 2 + startOffset + gapSize / 2,
                                              blockTemplate.getSize().y * i + startOffset + gapSize / 2,
                                              blockTemplate.getSize().x - gapSize,
                                              blockTemplate.getSize().y - gapSize,
                                              blockTemplate.getColor()));
                }
            }
        }
    }

    /**
     * @post A newly constructed ball is initialized
     */
    private void initBall() {
        Random randSign = new Random();

        double width = 20;
        double height = 20;
        
        double xPos = (size.x / 2) - height / 2;
        double yPos = 3 * size.y / 4;

        ball = new GameObject(xPos, yPos, width, height, Color.WHITE);
        ball.getVelocity().x = 1.6 * (randSign.nextBoolean() ? 1 : -1);
        ball.getVelocity().y = 1.6;
    }

    /**
     * Handles drawing the player in the correct position and moving the player based on user input
     * 
     * @post Player is drawn in new spot based on user input
     */
    private void movePlayer() {
        if (player.isEnabled()) {
            context.setFill(player.getColor());
            context.fillRect(player.getPosition().x, player.getPosition().y, player.getSize().x, player.getSize().y);
            
            if (!((Boolean) player.getAttribute("ballCollide"))) {
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
    }

    /**
     * Handles moving the ball based on collision
     * 
     * @post Ball is drawn moving in correct direction based on last collision
     * @return True if ball isn't colliding, false otherwise
     */
    private void moveBall() {
        context.setFill(ball.getColor());
        context.fillOval(ball.getPosition().x, ball.getPosition().y, ball.getSize().x, ball.getSize().y);

        boolean playerCollide = checkBallCollision(player);
        player.addAttribute("ballCollide", playerCollide);
        if (!playerCollide) {
            for (GameObject block : blocks) {
                if (block.isEnabled() && checkBallCollision(block)) {
                    block.disable();
                    break;
                }
            }
        }
        
        if (ball.getPosition().x <= EPSILON || size.x - (ball.getPosition().x + ball.getSize().x) <= EPSILON) { 
            ball.getVelocity().x *= -1;
        }

        ball.move();
    }

    private boolean checkBallCollision(GameObject gameObject) {
        boolean colliding = ball.getMaxBound().x > gameObject.getMinBound().x
                            && ball.getMinBound().x < gameObject.getMaxBound().x
                            && ball.getMaxBound().y > gameObject.getMinBound().y
                            && ball.getMinBound().y < gameObject.getMaxBound().y;
        if (colliding)
        {
            Vector center = new Vector(
                (ball.getMaxBound().x + ball.getMinBound().x) / 2,
                (ball.getMaxBound().y + ball.getMinBound().y) / 2
            );

            if (center.x > gameObject.getMinBound().x && center.x < gameObject.getMaxBound().x) {
                ball.getVelocity().y *= -1;
            } else {
                ball.getVelocity().x *= -1;
            }
        }

        return colliding;
    }
}
