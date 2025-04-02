package ca.mcmaster.se2aa4.mazerunner.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeImporter {

    private static final Logger logger = LogManager.getLogger();
    
    //Reads a maze file and converts it into a Maze object
    public static Maze scanMaze(String inputFile) throws IOException{

        logger.trace("Initializing maze scan...");
        logger.info("Reading maze file: " + inputFile);
        
        //Store maze lines in a list
        List<char[]> mazeLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                mazeLines.add(line.toCharArray()); //Convert each line into a char array
            }

        } catch (IOException e) {
            logger.error("An error occurred while reading the maze file: " + inputFile, e);
            throw e;
        }
        
        //Convert list to a 2D char array
        char[][] mazeGrid = mazeLines.toArray(new char[0][]);

        //Create Maze object
        Maze maze = new Maze(mazeGrid);
        logger.trace("Maze scan completed successfully");

        return maze;
    }
}
