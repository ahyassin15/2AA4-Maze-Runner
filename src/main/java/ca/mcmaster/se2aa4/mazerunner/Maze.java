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
        
        try (BufferedReader initialReader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            int totalRows = 0;
            
            while ((currentLine = initialReader.readLine()) != null) {
                totalRows++;
            }

            initialReader.close();

            mazeGrid = new String[totalRows][];
            BufferedReader secondReader = new BufferedReader(new FileReader(inputFile));
            int currentRow = 0;

            while ((currentLine = secondReader.readLine()) != null) {
                
                mazeGrid[currentRow] = new String[currentLine.length()];
                
                for (int index = 0; index < currentLine.length(); index++) {
                    if (currentLine.charAt(index) == '#') {
                        mazeGrid[currentRow][index] = "#";
                    } else if (currentLine.charAt(index) == ' ') {
                        mazeGrid[currentRow][index] = " ";
                    }
                }
                currentRow++;
            }
            secondReader.close();
            
        } catch (IOException e) {
            logger.error("An error occurred while reading the maze file", e);
        }
        return mazeGrid;
    }

    public String[][] getMazeGrid() {
        return mazeGrid;
    }

    public void setMazeGrid(String[][] mazeGrid) {
        this.mazeGrid = mazeGrid;
    }

    public void displayMaze() {
        for (int row = 0; row < mazeGrid.length; row++) {
            for (int col = 0; col < mazeGrid[row].length; col++) {
                logger.info(mazeGrid[row][col]);
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