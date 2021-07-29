package org.example;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashMap;

public class GameScene extends Scene {
    private HashMap<KeyCode, Boolean> keyBuffer;

    public GameScene(Parent root) {
        super(root);

        keyBuffer = new HashMap<>();

        setOnKeyPressed((event) -> keyBuffer.put(event.getCode(), true));
        setOnKeyReleased((event) -> keyBuffer.put(event.getCode(), false));
    }

    public boolean isKeyDown(KeyCode key) {
        Boolean retVal = keyBuffer.get(key);
        return retVal != null && retVal;
    }
}
