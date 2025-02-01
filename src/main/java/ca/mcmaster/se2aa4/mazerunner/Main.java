package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    private static Maze maze;
    private static MazeRunner mazeRunner;
    
    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");
    
        try {
            
            CommandLineProcessor cliProcessor = new CommandLineProcessor();
            CommandLine cmdLine = cliProcessor.process(args);

            if (!cmdLine.hasOption("i")) {
                logger.error("Missing required option: i");
                System.exit(1);
            }
            
            // Get the file path
            String filePath = cmdLine.getOptionValue("i");

            // Perform the maze walker if -p is provided
            if (cmdLine.hasOption("p")) {
                
                // Get the path
                String path = cmdLine.getOptionValue("p");
                

            } else {
                
                String inputFile = cmdLine.getOptionValue("i");
                logger.info("**** Reading the maze from file " + inputFile);
                
                //Instantiate Maze and MazeRunner
                maze = new Maze(inputFile);
                mazeRunner = new MazeRunner(maze.getMazeGrid(), maze.getEntryPoint(), maze.getExitPoint());
                
                //Execute the maze solving algorithm
                if (mazeRunner.mazeAlgorithm()) {
                    logger.info("Maze solved successfully.");
                } else {
                    logger.info("Failed to solve the maze.");
                }
            }
            
        } catch (ParseException parseEx) {
            logger.error("Error while parsing the command-line arguments", parseEx);
        } catch (Exception e) {
            logger.error("An error occurred while processing the maze", e);
        }
    }
}