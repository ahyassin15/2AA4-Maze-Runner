package ca.mcmaster.se2aa4.mazerunner.maze;

import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.core.Direction;
import ca.mcmaster.se2aa4.mazerunner.core.MazeCell;
import ca.mcmaster.se2aa4.mazerunner.core.Position;

public class MazeRunner {

    private static final Logger logger = LogManager.getLogger();    // Logger instance
    private Maze maze;                                              // Maze object (current maze)
    private Position position;                                      // Current position in the maze
    private Direction direction;                                    // Current facing direction

    // Constructor initializes MazeRunner at the maze's entry point, facing right by default
    public MazeRunner(Maze maze) {
        this.maze = maze;
        this.position = maze.getEntryPoint();
        this.direction = Direction.RIGHT;
    }

    // Verifies whether the provided path successfully reaches the maze exit
    public boolean verifyPath(Maze maze, String path) {
        
        logger.trace("Starting path verification...");

        // Convert path into its expanded (canonical) format
        MazePath newPath = new MazePath(path);

        // Process each movement in the path
        for (MazeStep instruction : newPath) {
            char c = instruction.toCharacter();
            
            if (c == 'L') {
                direction = direction.moveLeft();
            } else if (c == 'R') {
                direction = direction.moveRight();
            } else if (c == 'F') {
                Position nextPosition = position.move(direction);

                //If the new position is a wall
                if (!nextPosition.checkWithinBounds(maze.getDimensions())) {
                    return false;
                }

                if (maze.getCell(nextPosition) == MazeCell.WALL) {
                    return false;
                }
                
                //Update position
                position = nextPosition;
            }
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