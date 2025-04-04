package ca.mcmaster.se2aa4.mazerunner.core;

public enum Direction {

    UP(0, -1), 
    DOWN(0, 1), 
    LEFT(-1, 0), 
    RIGHT(1, 0);

    private int dx, dy; // Change in x & y

    private Direction left;     
    private Direction right;    
    private Direction opposite; 

    static {
        UP.left = LEFT;
        UP.right = RIGHT;
        UP.opposite = DOWN;

        DOWN.left = RIGHT;
        DOWN.right = LEFT;
        DOWN.opposite = UP;

        LEFT.left = DOWN;
        LEFT.right = UP;
        LEFT.opposite = RIGHT;

        RIGHT.left = UP;
        RIGHT.right = DOWN;
        RIGHT.opposite = LEFT;
    }

    //Constructor to store movement values for each direction
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    protected int getChangeinX() {
        return dx;
    }

    protected int getChangeinY() {
        return dy;
    }

    public Direction moveOpp() {
        return opposite;
    }

    public Direction moveLeft() {
        return left;
    }

    public Direction moveRight() {
        return right;
    }
}