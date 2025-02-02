package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver implements MazeSolver {

    private static final Logger logger = LogManager.getLogger(); //Logger instance

    @Override
    public String solveMaze(Maze maze) {

        logger.trace("Starting maze solution using the right-hand rule...");

        
        StringBuilder path = new StringBuilder();   //StringBuilder to store the movement path
        Direction direction = Direction.RIGHT;      //Initial direction
        Position position = maze.getEntryPoint();   //Initial position

        // Continue moving until reaching the maze exit
        while (!maze.checkExitPoint(position)) {
            
            // Determine possible moves
            Position leftTurn = position.move(direction.moveLeft());
            Position rightTurn = position.move(direction.moveRight());
            Position forwardMove = position.move(direction);

            // Check if walls exist in different directions
            boolean hasLeftWall = maze.checkWall(leftTurn);
            boolean hasRightWall = maze.checkWall(rightTurn);
            boolean hasFrontWall = maze.checkWall(forwardMove);

            // Right-hand rule decision making
            if (!hasRightWall) {
                
                // If there is no wall on the right, turn right and move forward
                direction = direction.moveRight();
                path.append("R");

            } else if (hasFrontWall) {
                
                // If facing a front wall, determine whether to turn left or reverse
                if (hasLeftWall) {
                    
                    // Dead end: turn around
                    direction = direction.moveOpp();
                    path.append("LL");
                
                } else {
                    
                    // Turn left if left side is open
                    direction = direction.moveLeft();
                    path.append("L");

                }
            }

            // Move forward in the chosen direction
            path.append("F");
            position = position.move(direction);
        }

        logger.trace("Maze solved successfully.");

        // Convert path to a factored format before returning
        return MazeRunner.toFactoredPath(path.toString());
    }
}