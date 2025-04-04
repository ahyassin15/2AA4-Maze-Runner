package ca.mcmaster.se2aa4.mazerunner.maze;

public class MazeLeftStep extends MazeStep{
    
    @Override
    public boolean execute(MazeRunner runner) {
        runner.makeLeftTurn();
        return true;
    }
 
    @Override
    public char toCharacter() {
        return 'L';
    }
}