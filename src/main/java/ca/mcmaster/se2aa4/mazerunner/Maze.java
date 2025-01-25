package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    
    private static final Logger logger = LogManager.getLogger();
    String[][] mazeGrid;

    public Maze(String inputFile) {
        scanMaze(inputFile);
    }

    public String[][] scanMaze(String inputFile) {
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int rowCount = 0;
            
            while ((line = reader.readLine()) != null) {
                rowCount++;
            }

            reader.close();

            mazeGrid = new String[rowCount][];
            BufferedReader reader2 = new BufferedReader(new FileReader(inputFile));
            int row = 0;

            while ((line = reader2.readLine()) != null) {
                
                mazeGrid[row] = new String[line.length()];
                
                for (int index = 0; index < line.length(); index++) {
                    if (line.charAt(index) == '#') {
                        mazeGrid[row][index] = "#";
                    } else if (line.charAt(index) == ' ') {
                        mazeGrid[row][index] = " ";
                    }
                }
                row++;
            }
            reader2.close();
            
        } catch (IOException e) {
            logger.error("An error occurred while reading the maze file", e);
        }
        return mazeGrid;
    }

    public String[][] getMazeGrid() {
        return mazeGrid;
    }

    public void setMazeArray(String[][] mazeGrid) {
        this.mazeGrid = mazeGrid;
    }

    public void printMaze() {
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
            if (mazeGrid[i][mazeGrid[i].length - 1].equals(" ")) {
                return new int[]{i, mazeGrid[i].length - 1};
            }
        }
        return null;
    }
}