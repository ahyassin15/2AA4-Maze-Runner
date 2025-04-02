package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        path = toCanonicalPath(path);

        // Process each movement in the path
        for (char step : path.toCharArray()) {
            switch (step) {
                case 'L' -> direction = direction.moveLeft();     // Turn left
                case 'R' -> direction = direction.moveRight();    // Turn right
                case 'F' -> {                                           
                    // Move forward in the current direction
                    Position nextPosition = position.move(direction);
                    
                    // If the new position is the exit, return true
                    if (maze.checkExitPoint(nextPosition)) {
                        return true;
                    }

                    position = nextPosition; // Update position
                }
            }
        }

        logger.trace("Path verification complete.");
        return maze.checkExitPoint(position); // Check if the final position is at the exit
    }

    // Converts compacted path into expanded form (e.g. "3F2L" into "FFFLL")
    public static String toCanonicalPath(String path) {
        logger.trace("Expanding path to canonical format...");

        StringBuilder expandedPath = new StringBuilder();
        int repeatCount = -1; // Stores number of times a movement should be repeated

        for (char current : path.toCharArray()) {
            if (Character.isDigit(current)) {
                repeatCount = Character.getNumericValue(current); // Store the numeric value
            } else {
                if (repeatCount > 0) {
                    expandedPath.append(String.valueOf(current).repeat(repeatCount)); // Repeat the movement
                    repeatCount = -1; // Reset repeat count
                } else {
                    expandedPath.append(current); // Append the movement as-is
                }
            }
        }

        String canonicalPath = expandedPath.toString();
        logger.trace("Canonical path: " + canonicalPath);

        return canonicalPath;
    }

    // Converts expanded path into compacted form (e.g. "FFFLL" into "3F2L")
    public static String toFactoredPath(String path) {
        logger.trace("Compacting path representation...");

        if (path.isEmpty()) return ""; // Return empty string if input is empty

        StringBuilder compactPath = new StringBuilder();
        char currentChar = path.charAt(0); // First movement character
        int count = 1; // Count occurrences of the current movement

        // Iterate through the path string
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == currentChar) {
                count++; // Increase count if it's the same movement
            } else {
                if (count > 1) compactPath.append(count); // Append count if greater than 1
                compactPath.append(currentChar); // Append movement character
                currentChar = path.charAt(i); // Update current movement
                count = 1; // Reset count
            }
        }

        // Append the last character sequence
        if (count > 1) compactPath.append(count);
        compactPath.append(currentChar);

        String factoredPath = compactPath.toString();
        logger.trace("Factored path: " + factoredPath);

        return factoredPath;
    }
}