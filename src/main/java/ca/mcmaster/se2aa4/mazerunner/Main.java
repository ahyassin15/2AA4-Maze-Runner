package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;


/**
 * Main class to run the Maze Runner application.
 * - Parses command-line arguments for the input maze file (-i)
 * - Initializes the Maze object from the specified file
 * - Creates a MazeRunner to navigate the loaded maze
 * - Executes maze-solving algorithm and logs whether the maze was solved
 */
public class Main {

    private static final Logger logger = LogManager.getLogger();

    private static Maze maze;
    private static MazeRunner mazeRunner;
    
    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");

        Options cliOptions = new Options();
        cliOptions.addOption("i", true, "Path to the maze input file");

        CommandLineParser cliParser = new DefaultParser();
        CommandLine cmdLine;
    
        try {
            
            cmdLine = cliParser.parse(cliOptions, args);
            
            //Check for input file
            if (cmdLine.hasOption("i")) {
                
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
            } else {
                logger.error("No input file specified. Use the -i option to provide a maze file.");
            }
            
        } catch (ParseException parseEx) {
            logger.error("Error while parsing the command-line arguments", parseEx);
        } catch (Exception e) {
            logger.error("An error occurred while processing the maze", e);
        }
    }
}