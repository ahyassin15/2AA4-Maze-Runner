package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    private static final Logger logger = LogManager.getLogger();

    String[][] mazeGrid;

    Maze(String inputFile) {
        scanMaze(inputFile);
    }

    public String[][] scanMaze(String inputFile) {

        String[][] maze = null;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                if (maze == null) {
                    maze = new String[line.length()][];
                }
                maze[row] = new String[line.length()];

                for (int index = 0; index < line.length(); index++) {
                    char currentChar = line.charAt(index);
                    if (currentChar == '#') {
                        logger.info("WALL ");
                        maze[row][index] = "#";
                    } else if (currentChar == ' ') {
                        logger.info("PASS ");
                        maze[row][index] = " ";
                    }
                }
                logger.info(System.lineSeparator());
                row++;
            }
        } catch (IOException e) {
            logger.error("Error reading file: " + e.getMessage());
        }
        return maze;
    }

    public String[][] getMazeArray() {
        return mazeGrid;
    }

    public void setMazeArray(String[][] mazeGrid) {
        this.mazeGrid = mazeGrid;
    }

    public void displayMaze() {

        for (int i = 0; i < mazeGrid.length; i++) {
            for (int j = 0; j < mazeGrid[i].length; j++) {
                logger.info(mazeGrid[i][j]);
            }
            logger.info(System.lineSeparator());
        }
        
    }

    public int[] getEntryPoint() {

        for (int i = 0; i < mazeGrid.length; i++) {
            if (mazeGrid[i][0].equals(" ")) {
                return new int[]{i, 0};
            }
        }
        return null;
    }

    public int[] getExitPoint() {

        for (int i = 0; i < mazeGrid.length; i++) {
            if (mazeGrid[i][mazeGrid[0].length - 1].equals(" ")) {
                return new int[]{i, mazeGrid[0].length - 1};
            }
        }
        return null;
    }
}
