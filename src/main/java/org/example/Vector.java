package org.example;

public class Vector {
    public double x;
    public double y;

    public Vector() {
        x = 0;
        y = 0;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector other) {
        x += other.x;
        y += other.y;
    }

    public void subtract(Vector other) {
        x -= other.x;
        y -= other.y;
    }

    public double dot(Vector other) {
        return x * other.x + y * other.y;
    }
}
