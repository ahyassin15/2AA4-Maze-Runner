package ca.mcmaster.se2aa4.mazerunner.maze;

import ca.mcmaster.se2aa4.mazerunner.core.MazeCell;
import ca.mcmaster.se2aa4.mazerunner.core.Position;

public class MazeFwdStep extends MazeStep{
    
    @Override
    public boolean execute(MazeRunner runner) {
        
        Maze maze = runner.getMaze();
 
        Position position = runner.goForward();
 
        if (!position.checkWithinBounds(maze.getDimensions())) {
            return false;
        }
 
        if (maze.getCell(position) == MazeCell.WALL) {
            return false;
        }
 
        return true;
    }
 
    @Override
    public char toCharacter() {
        return 'F';
    }
}
