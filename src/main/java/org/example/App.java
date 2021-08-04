package org.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class App extends Application {
    private final double WIDTH = 720;
    private final double HEIGHT = 900;

    private final int BLOCK_ROWS = 10;
    private final int BLOCK_COLS = 10;

    @Override
    public void start(Stage stage) {
        // Stage settings
        stage.setResizable(false);
        stage.setTitle("Scuffed Breakout");

        // Initial setup
        Group root = new Group();
        GameScene scene = new GameScene(root);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        root.getChildren().add(canvas);

        // Game
        GameAnimation game = new GameAnimation(scene, canvas, BLOCK_ROWS, BLOCK_COLS);
        game.start();

        // Start
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        // Vector a = new Vector(1, 1);
        // Vector b = new Vector(2, 3);

        // System.out.println(Vector.projection(a, b));
    }
}