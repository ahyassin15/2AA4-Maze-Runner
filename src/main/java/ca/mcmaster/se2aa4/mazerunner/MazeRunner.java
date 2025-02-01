package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MazeRunner {

    private static final Logger logger = LogManager.getLogger();

    private String canonicalPath = "";

    //Positions and grid
    private int[] currentPosition = new int[2];
    private int[] exitPosition = new int[2];
    private String[][] mazeGrid;

    //Direction flags
    private boolean isFacingNorth = false;
    private boolean isFacingSouth = false;
    private boolean isFacingEast = false;
    private boolean isFacingWest = true;

    public MazeRunner(String[][] mazeGrid, int[] startPoint, int[] exitPoint) {
        this.currentPosition = startPoint;
        this.exitPosition = exitPoint;
        this.mazeGrid = mazeGrid;
    }

    public void addToPath(String step) {
        canonicalPath += step;
    }

    public void removePath() {
        if (!canonicalPath.isEmpty() && canonicalPath.length() > 0) {
            canonicalPath = canonicalPath.substring(0, canonicalPath.length() - 1);
        }
    }

    public String[] getPath() {
        return canonicalPath.split("");
    }

    public int[] getPosition() {
        return currentPosition;
    }

    public void setPosition(int[] newPosition) {
        currentPosition = newPosition;
    }

    public void moveForward() {
        if (isFacingNorth) {
            currentPosition[0]--;
        } else if (isFacingSouth) {
            currentPosition[0]++;
        } else if (isFacingEast) {
            currentPosition[1]++;
        } else if (isFacingWest) {
            currentPosition[1]--;
        }
        addToPath("F");
    }

    public void moveLeft() {
        if (isFacingNorth) {
            isFacingWest = true;
            isFacingNorth = false;
        } else if (isFacingSouth) {
            isFacingEast = true;
            isFacingSouth = false;
        } else if (isFacingEast) {
            isFacingNorth = true;
            isFacingEast = false;
        } else if (isFacingWest) {
            isFacingSouth = true;
            isFacingWest = false;
        }
        addToPath("L");
    }

    public void moveRight() {
        if (isFacingNorth) {
            isFacingEast = true;
            isFacingNorth = false;
        } else if (isFacingSouth) {
            isFacingWest = true;
            isFacingSouth = false;
        } else if (isFacingEast) {
            isFacingSouth = true;
            isFacingEast = false;
        } else if (isFacingWest) {
            isFacingNorth = true;
            isFacingWest = false;
        }
        addToPath("R");
    }

    public boolean checkDeadEnd(int[] position) {
        int row = position[0];
        int col = position[1];
        int walls = 0;

        if (row > 0 && checkWall(new int[]{row - 1, col})) {
            walls++;
        }

        if (col > 0 && checkWall(new int[]{row, col - 1})) {
            walls++;
        }

        if (row < mazeGrid.length - 1 && checkWall(new int[]{row + 1, col})) {
            walls++;
        }

        if (col < mazeGrid[0].length - 1 && checkWall(new int[]{row, col + 1})) {
            walls++;
        }

        return walls > 2;
    }

    public boolean checkWall(int[] position) {
        int row = position[0];
        int col = position[1];

        if (mazeGrid[row][col].equals("#")) {
            return true;
        }

        return false;
    }
    
    public boolean isExit(int[] position) {
        return Arrays.equals(position, exitPosition);
    }

    public int[] lookForward() {

        if (isFacingNorth) {
            return new int[]{currentPosition[0] - 1, currentPosition[1]};
        
        } else if (isFacingSouth) {
            return new int[]{currentPosition[0] + 1, currentPosition[1]};
        
        } else if (isFacingEast) {
            return new int[]{currentPosition[0], currentPosition[1] + 1};
        
        } else if (isFacingWest) {
            return new int[]{currentPosition[0], currentPosition[1] - 1};

        }
        return currentPosition;
    }

    public int[] lookLeft() {
        if (isFacingNorth) {
            return new int[]{currentPosition[0], currentPosition[1] - 1};
        } else if (isFacingSouth) {
            return new int[]{currentPosition[0], currentPosition[1] + 1};
        } else if (isFacingEast) {
            return new int[]{currentPosition[0] + 1, currentPosition[1]};
        } else if (isFacingWest) {
            return new int[]{currentPosition[0] - 1, currentPosition[1]};
        }
        return currentPosition;
    }

    public int[] lookRight() {
        if (isFacingNorth) {
            return new int[]{currentPosition[0], currentPosition[1] + 1};
        } else if (isFacingSouth) {
            return new int[]{currentPosition[0], currentPosition[1] - 1};
        } else if (isFacingEast) {
            return new int[]{currentPosition[0] - 1, currentPosition[1]};
        } else if (isFacingWest) {
            return new int[]{currentPosition[0] + 1, currentPosition[1]};
        }
        return currentPosition;
    }

    public boolean mazeAlgorithm() {
        
        int moves = 0;

        while (!isExit(currentPosition)) {
            if (!checkWall(lookForward())) {
                moveForward();
            } else if (!checkWall(lookLeft())) {
                moveLeft();
                moveForward();
            } else if (!checkWall(lookRight())) {
                moveRight();
                moveForward();
            } else {
                moveRight();
                moveRight();
            }

            moves++;

            if (moves > 1000) {
                break;
            }
        }

        if (isExit(currentPosition)) {
            logger.info("Maze has been solved");
            logger.info("Path: " + canonicalPath);
            return true;

        } else {
            logger.info("Maze has not been solved");
            return false;
        }
    }
}