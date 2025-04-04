package ca.mcmaster.se2aa4.mazerunner.maze;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.core.Direction;
import ca.mcmaster.se2aa4.mazerunner.core.Position;

public class MazeRunner {

    private static final Logger logger = LogManager.getLogger();    // Logger instance
    private Maze maze;                                              // Maze object (current maze)
    private Position position;                                      // Current position in the maze
    private Direction direction;                                    // Current facing direction
    private MazePath path;                                          // MazePath object

    // Constructor initializes MazeRunner at the maze's entry point, facing right by default
    public MazeRunner(Maze maze, String path) {
        this.maze = maze;
        this.position = maze.getEntryPoint();
        this.direction = Direction.RIGHT;
        this.path = new MazePath(path);
    }

    public Maze getMaze() {
        return maze;
    }
    
    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position goForward() {
        return (position = position.move(direction));
    }

    public void makeLeftTurn() {
        direction = direction.moveLeft();
    }

    public void makeRightTurn() {
        direction = direction.moveRight();
    }

    // Verifies whether the provided path successfully reaches the maze exit
    public boolean verifyPath() {
        
        logger.trace("Starting path verification...");

        // Process each movement in the path
        for (MazeStep instruction : path) {
            if (!instruction.execute(this))
                 return false;
        }

        logger.trace("Path verification complete.");
        return maze.checkExitPoint(position); // Check if the final position is at the exit
    }

    //Converts path into expanded form ("2F3L4R" to "FFLLLRRRR")
    public static String toCanonicalPath(String path) {

        logger.trace("Expanding path to canonical format...");
        String canonicalPath = new MazePath(path).toCanonicalForm();
        logger.trace("Canonical path: " + canonicalPath);

        return canonicalPath;
    }

    //Converts path into factored form ("FFLLLRRRR" to "2F3L4R")
    public static String toFactoredPath(String path) {

        logger.trace("Expanding path to factored format...");
        String factoredPath = new MazePath(path).toFactoredForm(true);
        logger.trace("Factored path: " + factoredPath);

        return factoredPath;
    }
}