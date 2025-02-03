package ca.mcmaster.se2aa4.mazerunner;

public enum MazeCell {
    
    //Define enum constants
    WALL, PASSAGE;

    //Return appropriate enum constant based on character corresponding to wall or passage
    public static MazeCell fromChar(char c) {
        if (c == '#') {
            return WALL;
        } else if (c == ' ') {
            return PASSAGE;
        } else {
            throw new IllegalArgumentException("Invalid character: " + c);
        }
    }

    //Check if current cell is a wall
    public boolean checkWall() {
        return this == WALL;
    }
}