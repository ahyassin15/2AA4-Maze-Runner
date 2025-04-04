package ca.mcmaster.se2aa4.mazerunner.maze;

public abstract class MazeStep {

    public abstract boolean execute(MazeRunner runner);

    public abstract char toCharacter();
 
    public static MazeStep fromCharacter(char c) {
        switch (c) {
            case 'F':
                return new MazeFwdStep();
            case 'L':
                return new MazeLeftStep();
            case 'R':
                return new MazeRightStep();
            default:
                throw new IllegalArgumentException("Invalid character: " + c);
        }
    }
 
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        MazeStep that = (MazeStep) object;

        return toCharacter() == that.toCharacter();
    }

    @Override
    public String toString() {
        return String.valueOf(toCharacter());
    }
    
}