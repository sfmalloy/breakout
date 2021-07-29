package org.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class App extends Application {
    private final double WIDTH = 512;
    private final double HEIGHT = 512;

    @Override
    public void start(Stage stage) {
        // Stage settings
        stage.setResizable(false);

        // Initial setup
        Group root = new Group();
        GameScene scene = new GameScene(root);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        // Game
        GameAnimation game = new GameAnimation(scene, canvas);
        game.start();

        // Start
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}