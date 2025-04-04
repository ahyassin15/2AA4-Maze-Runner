package ca.mcmaster.se2aa4.mazerunner.maze;

public class MazeRightStep extends MazeStep {

    @Override
    public boolean execute(MazeRunner runner) {
        runner.makeRightTurn();
        return true;
     }
 
    @Override
    public char toCharacter() {
        return 'R';
    }
}
