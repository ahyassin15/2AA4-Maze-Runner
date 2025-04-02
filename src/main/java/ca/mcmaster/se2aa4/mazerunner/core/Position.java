package ca.mcmaster.se2aa4.mazerunner.core;

public class Position {

    private int x;
    private int y;

    //Default constructor initializing position to (0,0)
    public Position() {
        this(0,0);
    }

    //Constructor to initialize position
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Getter method for X
    public int getX() {
        return x;
    }

    //Setter method for X
    public void setX(int x) {
        this.x = x;
    }

    //Getter method for Y
    public int getY() {
        return y;
    }

    //Setter method for Y
    public void setY(int y) {
        this.y = y;
    }

    //Add two Position objects and return a new Position
    public Position add(Position additionalValue) {
        return new Position(this.x + additionalValue.x, this.y + additionalValue.y);
    }

    //Move the position in the given direction
    public Position move(Direction direction) {
        switch (direction) {
            case UP:
                return this.add(new Position(0, -1));
            case DOWN:
                return this.add(new Position(0, 1));
            case LEFT:
                return this.add(new Position(-1, 0));
            case RIGHT:
                return this.add(new Position(1, 0));
            default:
                throw new IllegalStateException("Unexpected direction: " + direction);
        }
    }

    //Override toString method
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}