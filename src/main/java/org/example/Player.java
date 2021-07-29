package org.example;

import javafx.scene.paint.Color;

public class Player {
    private double xPos;
    private double yPos;

    private double width;
    private double height;

    private Color color;

    public Player(double xPos, double yPos, double width, double height, Color fillColor) {
        this.xPos = xPos;
        this.yPos = yPos;

        this.width = width;
        this.height = height;

        this.color = fillColor;
    }

    public void moveLeft(double distance) {
        this.xPos -= distance;
    }

    public void moveRight(double distance) {
        this.xPos += distance;
    }

    public void moveUp(double distance) {
        this.yPos -= distance;
    }

    public void moveDown(double distance) {
        this.yPos += distance;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
}
