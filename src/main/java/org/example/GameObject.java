package org.example;

import javafx.scene.paint.Color;

import java.util.HashMap;

public class GameObject {
    private Vector position;
    private Vector velocity;
    private Vector size;

    private Color color;

    private HashMap<String, Object> attributes;

    private boolean enabled;

    public GameObject() {
        this.position = new Vector();
        this.velocity = new Vector();
        this.size = new Vector();
        this.color = Color.BLACK;
        this.attributes = new HashMap<>();
        this.enabled = true;
    }

    public GameObject(double xPos, double yPos, double width, double height, Color fillColor) {
        this.position = new Vector(xPos, yPos);
        this.velocity = new Vector();
        this.size = new Vector(width, height);
        this.color = fillColor;
        this.attributes = new HashMap<>();
        this.enabled = true;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void addAttribute(String name, Object attribute) {
        attributes.put(name, attribute);
    }

    public <T> T getAttribute(String name) {
        return (T) attributes.get(name);
    }

    public <T> void setAttribute(String name, T value) {
        attributes.put(name, value);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }
}
