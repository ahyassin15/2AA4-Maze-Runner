package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    
    //Enum constants
    UP, DOWN, LEFT, RIGHT;

    //Method to determine new direction when turning left
    public Direction moveLeft() {
        switch (this) {
            case UP: {
                return LEFT;
            }
            case DOWN: {
                return RIGHT;
            }
            case LEFT: {
                return DOWN;
            }
            case RIGHT: {
                return UP;
            }
        }
        throw new IllegalStateException("Unexpected direction: " + this);
    }

    //Method to determine new direction when turning right
    public Direction moveRight() {
        switch (this) {
            case UP: {
                return RIGHT;
            }
            case DOWN: {
                return LEFT;
            }
            case LEFT: {
                return UP;
            }
            case RIGHT: {
                return DOWN;
            }
        }
        throw new IllegalStateException("Unexpected direction: " + this);
    }

    //Method to determine new direction when going in the opposite direction
    public Direction moveOpp() {
        switch (this) {
            case UP: {
                return DOWN;
            }
            case DOWN: {
                return UP;
            }
            case LEFT: {
                return RIGHT;
            }
            case RIGHT: {
                return LEFT;
            }
        }
        throw new IllegalStateException("Unexpected direction: " + this);
    }

}