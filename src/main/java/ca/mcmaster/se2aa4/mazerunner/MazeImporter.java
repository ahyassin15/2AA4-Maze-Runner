package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeImporter {

    // Reads the maze file and stores it in the mazeGrid as CellType values
    public Cell[][] scanMaze(String inputFile) {
        
        try (BufferedReader initialReader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            int totalRows = 0;

            // Count number of rows in the file
            while ((currentLine = initialReader.readLine()) != null) {
                totalRows++;
            }

            initialReader.close();

            // Initialize mazeGrid with CellType
            mazeGrid = new Cell[totalRows][];
            BufferedReader secondReader = new BufferedReader(new FileReader(inputFile));
            int currentRow = 0;

            // Read the file again to populate the maze grid
            while ((currentLine = secondReader.readLine()) != null) {
                mazeGrid[currentRow] = new Cell[currentLine.length()];

                for (int index = 0; index < currentLine.length(); index++) {
                    mazeGrid[currentRow][index] = (currentLine.charAt(index) == '#') ? Cell.WALL : Cell.PASSAGE;
                }
                currentRow++;
            }
            secondReader.close();

        } catch (IOException e) {
            logger.error("An error occurred while reading the maze file", e);
        }
        return mazeGrid;
    }
}
