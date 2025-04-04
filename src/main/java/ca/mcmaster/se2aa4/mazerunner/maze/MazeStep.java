package ca.mcmaster.se2aa4.mazerunner.maze;
 
public enum MazeStep {

    FORWARD('F'), 
    LEFT('L'), 
    RIGHT('R');
 
    private char symbol;
 
    private MazeStep(char symbol) {
         this.symbol = symbol;
    }
 
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
 
    public char toCharacter() {
        return symbol;
    }
 
    public static MazeStep fromString(String symbol) {
        for (MazeStep instruction : MazeStep.values()) {
            if (instruction.toString().equals(symbol)) {
                return instruction;
            }
        }
        throw new IllegalArgumentException("Invalid maze path step: " + symbol);
    }
 
    public static MazeStep fromCharacter(char symbol) {
        for (MazeStep instruction : MazeStep.values()) {
            if (instruction.symbol == symbol) {
                return instruction;
            }
        }
        throw new IllegalArgumentException("Invalid maze path step: " + symbol);
    }
}