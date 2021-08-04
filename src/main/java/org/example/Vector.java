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

    /**
     * @return a + b
     */
    public static Vector add(Vector a, Vector b) {
        return new Vector(a.x + b.x, a.y + b.y);
    }

    /**
     * @return a - b
     */
    public static Vector subtract(Vector a, Vector b) {
        return new Vector(a.x - b.x, a.y - b.y);
    }

    public static double dot(Vector a, Vector b) {
        return a.x * b.x + a.y * b.y;
    }

    public static Vector scalarMultiply(double s, Vector v) {
        return new Vector(s * v.x, s * v.y);
    }

    /**
     * @return The projection of original onto axis
     */
    public static Vector projection(Vector axis, Vector original) {
        return scalarMultiply(dot(axis, original) / (axis.x * axis.x + axis.y * axis.y), axis);
    }

    @Override
    public String toString() {
        return String.format("{ %f, %f }\n", x, y);
    }

}
