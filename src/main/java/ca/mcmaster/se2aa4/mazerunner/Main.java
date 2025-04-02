package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeImporter;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeRunner;
import ca.mcmaster.se2aa4.mazerunner.solver.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.solver.RightHandSolver;

import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(); // Logger instance

    public static void main(String[] args) {
        
        logger.trace("Starting Maze Runner");

        try {
            
            // Process command-line arguments
            CLIArgumentHandler processor = new CLIArgumentHandler();
            CommandLine cmdLine = processor.process(args);

            // Ensure required input file (-i) is provided
            if (!cmdLine.hasOption("i")) {
                logger.error("Error: No input file provided. Use -i to specify the maze file.");
                System.exit(1);
            }

            // Retrieve the maze file path from arguments
            String filePath = cmdLine.getOptionValue("i");

            // Load and display the maze
            Maze maze = MazeImporter.scanMaze(filePath);
            // maze.displayMaze();

            logger.info("Processing the given path");

            // If a path is provided with -p, verify it
            if (cmdLine.hasOption("p")) {
                
                String providedPath = cmdLine.getOptionValue("p");

                MazeRunner mazeRunner = new MazeRunner(maze);

                if (mazeRunner.verifyPath(maze, providedPath)) {
                    System.out.println("Path is valid");
                } else {
                    System.out.println("Path is invalid");
                }

            } else {
                // If no path is given, solve the maze using the right-hand rule
                MazeSolver solver = new RightHandSolver();
                String pathSolution = solver.solveMaze(maze);
                System.out.println(pathSolution);
            }

            logger.trace("End of Maze Runner");

        } catch (Exception e) {
            logger.error("Unexpected error occurred", e);
            //e.printStackTrace();
        }
    }
}